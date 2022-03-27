package main.controller;

import main.api.response.InitResponse;
import main.api.response.SettingResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {
    private final InitResponse initResponse;

    public ApiGeneralController(InitResponse initResponse) {
        this.initResponse = initResponse;
    }

    @GetMapping("/init")
    private InitResponse init(){
        return initResponse;
    }

    @GetMapping("/settings")
    public SettingResponse settingResponse(){
        return new SettingResponse();
    }
}
