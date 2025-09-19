package ca.lazanomentsoa.monbeaujardinbackv2.main.dto.requests;

import lombok.Data;

@Data
public class EcolagePayRequestDto {
    private Integer idEtudiant;
    private byte jour;
    private byte mois;
    private short annee;
    private boolean payed;
}
