package ca.lazanomentsoa.monbeaujardinbackv2.main.repository;

import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Ecolage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcolageRepository extends JpaRepository<Ecolage, Integer> {
    @Query("SELECT ecolage FROM Ecolage ecolage JOIN ecolage.etudiant etudiant WHERE etudiant.id = :idEtudiant AND ecolage.mois = :mois AND ecolage.annee = :annee")
    Ecolage getEcolageByIdEtudiantAndMoisAndAnnee(@Param("idEtudiant") int idEtudiant, @Param("mois") byte mois, @Param("annee") short annee);
}
