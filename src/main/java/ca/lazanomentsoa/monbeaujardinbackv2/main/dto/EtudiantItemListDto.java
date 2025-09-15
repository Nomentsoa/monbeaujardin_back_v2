package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.Data;

@Data
public class EtudiantItemListDto {
    protected Integer id;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String dateNaissance;
    protected String etat;
    protected String image;
    protected Character sexe;
}
