package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.Data;

@Data
public class EtudiantDetailDto {
    private Integer id;
    private String matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresse;
    private Character sexe;
    private String noteSupplementaire;
    private String nomMere;
    private String telephoneMere;
    private String professionMere;
    private String nomPere;
    private String telephonePere;
    private String professionPere;
    private String nomTuteur;
    private String telephoneTuteur;
    private String professionTuteur;
    private Integer nombreFraternite;
}
