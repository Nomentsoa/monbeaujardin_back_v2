package ca.lazanomentsoa.monbeaujardinbackv2.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EtudiantEcolageDto extends EtudiantItemListDto{
    private byte jour;
    private byte mois;
    private short annee;
    private boolean isPayed;

    public void setEtudiantItemDto(EtudiantItemListDto etudiantItemListDto) {
        this.id = etudiantItemListDto.getId();
        this.matricule = etudiantItemListDto.getMatricule();
        this.nom = etudiantItemListDto.getNom();
        this.prenom = etudiantItemListDto.getPrenom();
        this.dateNaissance = etudiantItemListDto.getDateNaissance();
        this.etat = etudiantItemListDto.getEtat();
        this.image = etudiantItemListDto.getImage();
        this.sexe = etudiantItemListDto.getSexe();
    }
}
