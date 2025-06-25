package ca.lazanomentsoa.monbeaujardinbackv2.security.dao;

import ca.lazanomentsoa.monbeaujardinbackv2.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
