package ca.lazanomentsoa.monbeaujardinbackv2.main.mappers;


import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EcolageDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.entities.Ecolage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service

public class EcolageMapper {
    public Ecolage fromEcolageDtoToEcolage(EcolageDto ecolageDto) {
        Ecolage ecolage = new Ecolage();
        BeanUtils.copyProperties(ecolageDto, ecolage);
        return ecolage;
    }

    public EcolageDto fromEcolageToEcolageDto(Ecolage ecolage) {
        EcolageDto ecolageDto = new EcolageDto();
        BeanUtils.copyProperties(ecolage, ecolageDto);
        return ecolageDto;
    }

}
