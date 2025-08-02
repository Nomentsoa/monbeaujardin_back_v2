package ca.lazanomentsoa.monbeaujardinbackv2.main.web;

import ca.lazanomentsoa.monbeaujardinbackv2.main.services.DernierMatriculService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("${api.version}/matricul")
@AllArgsConstructor
public class DernierMatriculController {

    private DernierMatriculService dernierMatriculService;

    @GetMapping("")
    public String getDernierMatricul(@RequestParam(name = "appartenant") String appartenant) {
        return dernierMatriculService.getDernierMatricul(appartenant);
    }
}
