package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantItemListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.PageEtudiantListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.DernierMatricul;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.enums.MatriculAppartenant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.mappers.EtudiantMapper;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.DernierMatriculRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;
    private DernierMatriculService dernierMatriculService;
    @Override
    public PageEtudiantListDto getPageEtudiantListDto(String keyword, int page, int size) {
        PageEtudiantListDto pageEtudiantListDto = new PageEtudiantListDto();
        Page<Etudiant> pagedEtudiant = etudiantRepository.getAllEtudiantByKeyword(keyword, PageRequest.of(page, size));
        List<EtudiantItemListDto> etudiantItemListDtoList = pagedEtudiant.getContent().stream().map(etudiantMapper::toEtudiantItemListDto).collect(Collectors.toList());
        pageEtudiantListDto.setEtudiants(etudiantItemListDtoList);
        pageEtudiantListDto.setCurrentPage(page);
        pageEtudiantListDto.setTotalPages(pagedEtudiant.getTotalPages());


        return pageEtudiantListDto;
    }

    @Override
    public ReponseDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto) {
        ReponseDto reponseDto = new ReponseDto();
        try {
            Etudiant savedEtudiant = etudiantRepository.save(etudiantMapper.fromEtudiantDetailDtoToEtudiant(etudiantDetailDto));
            dernierMatriculService.setDernierMatricul(savedEtudiant.getMatricule(), MatriculAppartenant.ETUDIANT.toString());
            reponseDto.setIsError(false);
            reponseDto.setMessage(null);

        } catch (DataIntegrityViolationException e) {
            reponseDto.setIsError(true);
            reponseDto.setMessage("Numero Matricul déjà utilisé");
        }catch (Exception e){
            reponseDto.setIsError(true);
            reponseDto.setMessage(e.getMessage());
        }
        return reponseDto;
    }
}
