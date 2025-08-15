package ca.lazanomentsoa.monbeaujardinbackv2.main.web;

import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantDetailDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.EtudiantUpdateDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.PageEtudiantListDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.dto.ReponseDto;
import ca.lazanomentsoa.monbeaujardinbackv2.main.services.EtudiantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping(value = "${api.version}/etudiant")
@AllArgsConstructor
public class EtudiantController {
    private EtudiantService etudiantService;

    @GetMapping("")
    public ResponseEntity<PageEtudiantListDto> getAllEtudiants(@RequestParam(name = "keyword", defaultValue = "") String keyWord, @RequestParam(name = "etat", defaultValue = "I") String etat,
                                                                @RequestParam(name ="page", defaultValue = "0") int page,
                                                               @RequestParam(name = "size", defaultValue = "10") int size){
        log.info(EtudiantController.class.getSimpleName(), "getAllEtudiants");
        return new ResponseEntity<>(etudiantService.getPageEtudiantListDto(keyWord, etat, page, size), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<ReponseDto> addEtudiant(@RequestBody EtudiantDetailDto etudiantDetailDto){
        log.info(EtudiantController.class.getSimpleName(), "addEtudiant");
        return new ResponseEntity<>(etudiantService.saveEtudiantDetailDto(etudiantDetailDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDetailDto> getEtudiantDetailDto(@PathVariable("id") int id){
        log.info(EtudiantController.class.getSimpleName(), "getEtudiantDetail " + id);
        return new ResponseEntity<>(etudiantService.getEtudiantDetailBy(id), HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<ReponseDto> updateEtudiant(@RequestBody EtudiantUpdateDto etudiantUpdateDto){
        log.info(EtudiantController.class.getSimpleName(), "updateEtudiant");
        return new ResponseEntity<>(etudiantService.updateEtudiant(etudiantUpdateDto), HttpStatus.OK);
    }

}
