package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.PageEtudiantListDto;

public interface EtudiantService {
    PageEtudiantListDto getPageEtudiantListDto(String keyword, int page, int size);
    EtudiantDetailDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto);
}
