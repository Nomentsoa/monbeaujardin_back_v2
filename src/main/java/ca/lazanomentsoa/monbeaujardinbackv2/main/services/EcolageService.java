package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EcolageDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.requests.EcolagePayRequestDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Ecolage;

public interface EcolageService {
    EcolageDto getEcolageByIdEtudiantAndMoisAndAnnee(int idEtudiant, byte mois, short annee);
    ReponseDto addEcolageToEtudiant(EcolagePayRequestDto ecolagePayRequestDto);
}
