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
        return etudiantItemListDto;

    }

    public Etudiant fromEtudiantDetailDtoToEtudiant(EtudiantDetailDto etudiantDetailDto){
        Etudiant etudiant = new Etudiant();
        BeanUtils.copyProperties(etudiantDetailDto, etudiant);
        return etudiant;
    }
}
