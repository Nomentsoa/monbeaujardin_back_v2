package ca.lazanomentsoa.monbeaujardinbackv2.main.mappers;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantItemListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EtudiantMapper {
    public EtudiantItemListDto toEtudiantItemListDto(Etudiant etudiant){
        EtudiantItemListDto etudiantItemListDto = new EtudiantItemListDto();
        BeanUtils.copyProperties(etudiant, etudiantItemListDto);
        // set matricul etudiant Ex: 25/12-G
        etudiantItemListDto.setMatricule(Integer.valueOf(etudiant.getAnneeInscription().substring(1,4))+"/"+etudiant.getMatricule()+"-"+etudiant.getSexe());
        return etudiantItemListDto;

    }

    public Etudiant fromEtudiantDetailDtoToEtudiant(EtudiantDetailDto etudiantDetailDto){
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(etudiantDetailDto, etudiant);
        return etudiant;
    }

    public EtudiantDetailDto toEtudiantDetailDto(Etudiant etudiant){
        EtudiantDetailDto etudiantDetailDto = new EtudiantDetailDto();
        BeanUtils.copyProperties(etudiant, etudiantDetailDto);
        etudiantDetailDto.setMatricule(Integer.valueOf(etudiant.getAnneeInscription().substring(1,4))+"/"+etudiant.getMatricule()+"-"+etudiant.getSexe());
        return etudiantDetailDto;
    }
}
