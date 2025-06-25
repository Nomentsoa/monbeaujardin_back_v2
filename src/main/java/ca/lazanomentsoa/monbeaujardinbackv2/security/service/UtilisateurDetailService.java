package ca.lazanomentsoa.monbeaujardinbackv2.security.service;


import ca.lazanomentsoa.monbeaujardinbackv2.security.dao.UtilisateurRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtilisateurDetailService implements UserDetailsService {
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur " + username + " not found"));
        return new Utilisateur(utilisateur.getUsername(), utilisateur.getPassword(), utilisateur.getRoles());
    }
}
