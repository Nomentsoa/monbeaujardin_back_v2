package ca.lazanomentsoa.monbeaujardinbackv2.main.web;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EcolageDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.requests.EcolagePayRequestDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.services.EcolageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping(value = "${api.version}/ecolage")
public class EcolageController {
    private EcolageService ecolageService;

    @PostMapping("")
    public ResponseEntity<ReponseDto> payEcolage(@RequestBody EcolagePayRequestDto ecolagePayRequestDto){
        log.info(EcolageController.class.getSimpleName(), "payement ecolage");
        return new ResponseEntity<>(ecolageService.addEcolageToEtudiant(ecolagePayRequestDto), HttpStatus.OK);
    }
}
