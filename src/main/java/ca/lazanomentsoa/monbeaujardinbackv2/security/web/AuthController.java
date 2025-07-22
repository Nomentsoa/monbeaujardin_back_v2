package ca.lazanomentsoa.monbeaujardinbackv2.security.web;

import ca.lazanomentsoa.monbeaujardinbackv2.security.dao.RoleRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.security.dao.UtilisateurRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.security.dto.LoginDto;
import ca.lazanomentsoa.monbeaujardinbackv2.security.dto.RegisterDto;
import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Role;
import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Utilisateur;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/authentification")
@Slf4j

public class AuthController {

    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if(utilisateurRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Utilisateur déjà existant", HttpStatus.BAD_REQUEST);
        }

        log.info("je suis bien dans creation d'utilisateur " + registerDto.getPassword());
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(registerDto.getUsername());
        utilisateur.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName(registerDto.getRole()).get();
        utilisateur.setRoles(Collections.singletonList(role));

        utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>("Utilisateur enregistré", HttpStatus.CREATED);
    }

    @PostMapping("token")
    public ResponseEntity<Map<String, String>> jwtToken(@RequestBody LoginDto loginDto){
        String subject = null;
        String scope = null;

        if(loginDto.getGrantType().equals("password")){
            Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            subject = authentication.getName();
            scope = authentication.getAuthorities().stream().map(
                    auth -> auth.getAuthority()
            ).collect(Collectors.joining(" "));
        }else if(loginDto.getGrantType().equals("refreshToken")){
            if(loginDto.getRefreshToken() == null){
                return new ResponseEntity<>(Map.of("errorMessage", "Refresh Token est requis"), HttpStatus.UNAUTHORIZED);
            }

            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(loginDto.getRefreshToken());
            }catch (JwtException e){
                return new ResponseEntity<>(Map.of("errorMessage", e.getMessage()), HttpStatus.UNAUTHORIZED);
            }

            subject = decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(
                    GrantedAuthority::getAuthority
            ).collect(Collectors.joining(" "));
        }

        Map<String, String> idToken = new HashMap<>();
        Instant instant = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(loginDto.getWithRefreshToken()?5:30, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken", jwtAccessToken);

        if(loginDto.getWithRefreshToken()){
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();

            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refreshToken", jwtRefreshToken);
        }

        return new ResponseEntity<>(idToken, HttpStatus.CREATED);
    }
}
