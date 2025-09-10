package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.Data;

@Data
public class EcolageDto {
    private byte jour;
    private byte mois;
    private short annee;
    private boolean isPayed;
}
