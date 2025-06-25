package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantItemListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.PageEtudiantListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.mappers.EtudiantMapper;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;
    @Override
    public PageEtudiantListDto getPageEtudiantListDto(String keyword, int page, int size) {
        PageEtudiantListDto pageEtudiantListDto = new PageEtudiantListDto();
        Page<Etudiant> pagedEtudiant = etudiantRepository.getAllEtudiantByKeyword(keyword, PageRequest.of(page, size));
        List<EtudiantItemListDto> etudiantItemListDtoList = pagedEtudiant.getContent().stream().map(etudiantMapper::toEtudiantItemListDto).collect(Collectors.toList());
        pageEtudiantListDto.setEtudiants(etudiantItemListDtoList);
        pageEtudiantListDto.setCurrentPage(page);
        pageEtudiantListDto.setTotalPages(size);
        pageEtudiantListDto.setTotalPages(pagedEtudiant.getTotalPages());

        return pageEtudiantListDto;
    }

    @Override
    public EtudiantDetailDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto) {
        Etudiant savedEtudiant = etudiantRepository.save(etudiantMapper.fromEtudiantDetailDtoToEtudiant(etudiantDetailDto));
        return etudiantDetailDto;
    }
}
