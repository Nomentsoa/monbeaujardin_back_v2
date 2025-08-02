package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.PageEtudiantListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;

public interface EtudiantService {
    PageEtudiantListDto getPageEtudiantListDto(String keyword, int page, int size);
    ReponseDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto);
}
