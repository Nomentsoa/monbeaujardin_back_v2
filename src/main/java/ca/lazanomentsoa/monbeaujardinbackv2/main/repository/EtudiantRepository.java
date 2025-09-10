package ca.lazanomentsoa.monbeaujardinbackv2.main.repository;


import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantEcolageDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    @Query("select etudiant from Etudiant etudiant where etudiant.etat= :etat and (etudiant.nom like %:keyword% or etudiant.prenom like %:keyword% or etudiant.matricule like %:keyword%) order by etudiant.matricule")
    Page<Etudiant> getAllEtudiantByKeyword(@Param("keyword") String keyword, @Param("etat") String etat,  Pageable pageable);

//    @Query("SELECT new ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantEcolageDto(etudiant.id, etudiant.matricule, etudiant.nom, etudiant.prenom, etudiant.sexe, etudiant.dateNaissance, etudiant.image, ecolage.mois, ecolage.jour, ecolage.annee) FROM Etudiant etudiant JOIN etudiant.ecolagesEtudiants ecolage WHERE (etudiant.nom like %:keyword% or etudiant.prenom like %:keyword% or etudiant.matricule like %:keyword%) AND  ecolage.mois = :mois AND  ecolage.annee = :annee order by etudiant.matricule")
//    Page<EtudiantEcolageDto> getListEtudiantEcoloageByMoisAndAnnee(@Param("keyword") String keyword, @Param("mois") byte mois, @Param("annee") Short annee, Pageable pageable);

}
