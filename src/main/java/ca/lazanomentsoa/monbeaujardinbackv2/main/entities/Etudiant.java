package ca.lazanomentsoa.monbeaujardinbackv2.main.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Etudiant extends Personne{

    private String nomMere;
    @Column(length = 10)
    private String telephoneMere;
    private String professionMere;
    private String nomPere;
    @Column(length = 10)
    private String telephonePere;
    private String professionPere;
    private String nomTuteur;
    @Column(length = 10)
    private String telephoneTuteur;
    private String professionTuteur;
    private Integer nombreFraternite;
    @Column(length = 1)
    private String etat;
}
