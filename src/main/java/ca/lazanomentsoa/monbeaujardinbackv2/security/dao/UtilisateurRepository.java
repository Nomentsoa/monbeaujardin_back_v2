package ca.lazanomentsoa.monbeaujardinbackv2.security.dao;

import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur>findByUsername(String username);
    Boolean existsByUsername(String username);
}
