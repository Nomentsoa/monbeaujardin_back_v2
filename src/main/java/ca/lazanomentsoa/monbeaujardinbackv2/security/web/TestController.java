package ca.lazanomentsoa.monbeaujardinbackv2.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.version}/test")
public class TestController {
    @GetMapping("")
    public String test(){
        return "test";
    }
}
