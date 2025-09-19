package ca.lazanomentsoa.monbeaujardinbackv2.main.services;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.*;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Ecolage;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.enums.MatriculAppartenant;
import ca.lazanomentsoa.monbeaujardinbackv2.main.mappers.EtudiantMapper;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.EcolageRepository;
import ca.lazanomentsoa.monbeaujardinbackv2.main.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService{
    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;
    private DernierMatriculService dernierMatriculService;
    private EcolageRepository ecolageRepository;
    @Override
    public PageEtudiantListDto getPageEtudiantListDto(String keyword,String etat,  int page, int size) {
        PageEtudiantListDto pageEtudiantListDto = new PageEtudiantListDto();
        Page<Etudiant> pagedEtudiant = etudiantRepository.getAllEtudiantByKeyword(keyword,etat, PageRequest.of(page, size));
        List<EtudiantItemListDto> etudiantItemListDtoList = pagedEtudiant.getContent().stream().map(etudiantMapper::toEtudiantItemListDto).collect(Collectors.toList());
        pageEtudiantListDto.setEtudiants(etudiantItemListDtoList);
        pageEtudiantListDto.setCurrentPage(page);
        pageEtudiantListDto.setTotalPages(pagedEtudiant.getTotalPages());


        return pageEtudiantListDto;
    }

    @Override
    public ReponseDto saveEtudiantDetailDto(EtudiantDetailDto etudiantDetailDto) {
        try {
            Etudiant savedEtudiant = etudiantRepository.save(etudiantMapper.fromEtudiantDetailDtoToEtudiant(etudiantDetailDto));
            dernierMatriculService.setDernierMatricul(savedEtudiant.getMatricule(), MatriculAppartenant.ETUDIANT.toString());
            return new ReponseDto(false, "Saved successfully");
        } catch (DataIntegrityViolationException e) {
            return new ReponseDto(true, "Numero Matricul déjà utilisé");
        }catch (Exception e){
            return new ReponseDto(true, e.getMessage());
        }
    }

    @Override
    public EtudiantDetailDto getEtudiantDetailBy(int id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        return etudiantMapper.toEtudiantDetailDto(etudiant);
    }

    @Override
    public ReponseDto updateEtudiant(EtudiantUpdateDto etudiantUpdateDto) {
        Etudiant etudiantToUpdate = etudiantRepository.findById(etudiantUpdateDto.getId()).orElse(null);
        if(etudiantToUpdate != null){
            etudiantToUpdate.setEtat(etudiantUpdateDto.getEtat());
            etudiantToUpdate.setNombreFraternite(etudiantUpdateDto.getNombreFraternite());
            etudiantToUpdate.setTelephoneMere(etudiantUpdateDto.getTelephoneMere());
            etudiantToUpdate.setProfessionMere(etudiantUpdateDto.getProfessionMere());
            etudiantToUpdate.setTelephonePere(etudiantUpdateDto.getTelephonePere());
            etudiantToUpdate.setProfessionPere(etudiantUpdateDto.getProfessionPere());
            etudiantToUpdate.setTelephoneTuteur(etudiantUpdateDto.getTelephoneTuteur());
            etudiantToUpdate.setProfessionTuteur(etudiantUpdateDto.getProfessionTuteur());
            etudiantToUpdate.setAdresse(etudiantUpdateDto.getAdresse());
            etudiantToUpdate.setNoteSupplementaire(etudiantUpdateDto.getNoteSupplementaire());

            etudiantRepository.save(etudiantToUpdate);
            return new ReponseDto(false, "Updated");

        }else{
            return new ReponseDto(true, "Etudiant null");
        }
    }

    @Override
    public PagedEtudiantEcolageDto getPagedEtudiantEcolageDto(String keyword, String etat, int page, int size, byte mois, short annee) {
        PagedEtudiantEcolageDto pagedEtudiantEcolageDtoListDto = new PagedEtudiantEcolageDto();
        PageEtudiantListDto pageEtudiantListDto = getPageEtudiantListDto(keyword, etat, page, size);
        pagedEtudiantEcolageDtoListDto.setCurrentPage(page);
        pagedEtudiantEcolageDtoListDto.setTotalPages(pageEtudiantListDto.getTotalPages());
        pagedEtudiantEcolageDtoListDto.setPageSize(pageEtudiantListDto.getPageSize());
        List<EtudiantEcolageDto> etudiantEcolageDtos = new ArrayList<>();
        pageEtudiantListDto.getEtudiants().forEach(etudiantItemListDto -> {
            EtudiantEcolageDto etudiantEcolageDto = new EtudiantEcolageDto();
            etudiantEcolageDto.setEtudiantItemDto(etudiantItemListDto);
            Ecolage ecolageEtat =  ecolageRepository.getEcolageByIdEtudiantAndMoisAndAnnee(etudiantItemListDto.getId(), mois, annee);
            if(ecolageEtat != null){
                etudiantEcolageDto.setMois(ecolageEtat.getMois());
                etudiantEcolageDto.setAnnee(ecolageEtat.getAnnee());
                etudiantEcolageDto.setJour(ecolageEtat.getJour());
                etudiantEcolageDto.setPayed(ecolageEtat.isPayed());
            }
            etudiantEcolageDtos.add(etudiantEcolageDto);
        });

        pagedEtudiantEcolageDtoListDto.setEtudiantEcolages(etudiantEcolageDtos);


        return pagedEtudiantEcolageDtoListDto;
    }

}
