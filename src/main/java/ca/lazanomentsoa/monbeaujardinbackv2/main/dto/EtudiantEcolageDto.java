package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EtudiantEcolageDto {
    private Integer idEtudiant;
    private String matriculeEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private Character sexeEtudiant;
    private String dateNaissanceEtudiant;
    private String imageEtudiant;
    private byte moisEcolage;
    private byte jourEcolage;
    private short anneeEcolage;
}
