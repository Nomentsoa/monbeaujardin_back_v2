package ca.lazanomentsoa.monbeaujardinbackv2.main.web;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EcolageDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.services.EcolageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping(value = "${api.version}/ecolage")
public class EcolageController {
    private EcolageService ecolageService;

    @GetMapping("")
    public ResponseEntity<EcolageDto> getEcolageEtudiant(@RequestParam(name = "idEtudiant") int idEtudiant, @RequestParam(name = "mois") byte mois, @RequestParam("annee") short annee){
        log.info(EcolageController.class.getSimpleName(), "getEcolageEtudiant");
        return new ResponseEntity<>(ecolageService.getEcolageByIdEtudiantAndMoisAndAnnee(idEtudiant, mois, annee), HttpStatus.OK);
    }
}
