package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantUpdateDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.PageEtudiantListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import org.springframework.http.ResponseEntity;

public interface EtudiantService {
    PageEtudiantListDto getPageEtudiantListDto(String keyword, String etat, int page, int size);
    ReponseDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto);
    EtudiantDetailDto getEtudiantDetailBy(int id);
    ReponseDto updateEtudiant(EtudiantUpdateDto etudiantUpdateDto);
}
