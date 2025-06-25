package ca.lazanomentsoa.monbeaujardinbackv2.security.web;

import ca.lazanomentsoa.monbeaujardinbackv2.security.dao.RoleRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.security.dao.UtilisateurRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.security.dto.RegisterDto;
import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Role;
import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Utilisateur;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authentification")
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

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(registerDto.getUsername());
        utilisateur.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role role = roleRepository.findByName(registerDto.getRole()).get();
        utilisateur.setRoles(Collections.singletonList(role));

        utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>("Utilisateur enregistré", HttpStatus.CREATED);
    }


    @PostMapping("token")
    public ResponseEntity<Map<String, String>> jwtToken(String grantType, String username, String password, boolean withRefreshToken, String refreshToken){
        String subject = null;
        String scope = null;

        if(grantType.equals("password")){
            Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(username, password)
            );

            subject = authentication.getName();
            scope = authentication.getAuthorities().stream().map(
                    auth -> auth.getAuthority()
            ).collect(Collectors.joining(" "));
        }else if(grantType.equals("refreshToken")){
            if(refreshToken == null){
                return new ResponseEntity<>(Map.of("errorMessage", "Refresh Token est requis"), HttpStatus.UNAUTHORIZED);
            }

            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(refreshToken);
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
                .expiresAt(instant.plus(withRefreshToken?5:30, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("access_token", jwtAccessToken);

        if(withRefreshToken){
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();

            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refresh_token", jwtRefreshToken);
        }

        return new ResponseEntity<>(idToken, HttpStatus.CREATED);
    }
}
