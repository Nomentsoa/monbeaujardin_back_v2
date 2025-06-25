package ca.lazanomentsoa.monbeaujardinbackv2.main.repository;


import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    @Query("select etudiant from Etudiant etudiant where etudiant.nom like %:keyword% or etudiant.prenom like %:keyword% or etudiant.matricule like %:keyword% order by etudiant.matricule")
    Page<Etudiant> getAllEtudiantByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
