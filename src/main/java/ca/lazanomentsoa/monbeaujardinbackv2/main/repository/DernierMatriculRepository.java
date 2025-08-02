package ca.lazanomentsoa.monbeaujardinbackv2.main.repository;

import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.DernierMatricul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DernierMatriculRepository extends JpaRepository<DernierMatricul, Integer> {

    @Query("SELECT dm FROM DernierMatricul dm  WHERE dm.appartenant = ?1 ORDER BY dm.id LIMIT 1")
    DernierMatricul getDernierMatricul(String appartenant);
}
