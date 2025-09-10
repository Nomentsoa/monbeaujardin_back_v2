package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.*;


public interface EtudiantService {
    PageEtudiantListDto getPageEtudiantListDto(String keyword, String etat, int page, int size);
    ReponseDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto);
    EtudiantDetailDto getEtudiantDetailBy(int id);
    ReponseDto updateEtudiant(EtudiantUpdateDto etudiantUpdateDto);

}
