package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.Data;

@Data
public class EtudiantUpdateDto {
    private Integer id;
    private String etat;
    private Integer nombreFraternite;
    private String telephoneMere;
    private String professionMere;
    private String telephonePere;
    private String professionPere;
    private String telephoneTuteur;
    private String professionTuteur;
    private String adresse;
    private String noteSupplementaire;
}
