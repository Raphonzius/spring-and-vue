package io.github.raphonzius.backend_spring.api.v1.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/lambda")
public class LambdaController {

    @PostMapping("wpp")
    public void receiveWppData(@RequestBody String data) {
        log.info("{} sent.", data);
    }
}
