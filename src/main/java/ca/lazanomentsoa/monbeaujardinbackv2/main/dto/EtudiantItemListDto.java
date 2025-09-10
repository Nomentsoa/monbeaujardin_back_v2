package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.Data;

@Data
public class EtudiantItemListDto {
    private Integer id;
    private String matricule;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String etat;
    private String image;
    private Character sexe;
}
