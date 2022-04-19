package main.controller;

import main.api.response.CheckUserResponse;
import main.api.response.InitResponse;
import main.api.response.PostsResponse;
import main.api.response.SettingResponse;
import main.service.CheckUserService;
import main.service.PostsService;
import main.service.SettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiGeneralController {
    private final InitResponse initResponse;
    private final SettingsService settingsService;
    private final CheckUserService checkUserService;
    private final PostsService postsService;

    public ApiGeneralController(InitResponse initResponse,
                                SettingsService settingsService,
                                CheckUserService checkUserService,
                                PostsService postsService) {
        this.initResponse = initResponse;
        this.settingsService = settingsService;
        this.checkUserService = checkUserService;
        this.postsService = postsService;
    }

    @GetMapping("/init")
    private InitResponse init(){
        return initResponse;
    }

    @GetMapping("/settings")
    public ResponseEntity<SettingResponse> settingsResponse(){
        SettingResponse settingResponse = settingsService.getGlobalSettings();
        if(settingResponse == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(settingResponse);
    }

    @GetMapping("/auth/check")
    public ResponseEntity<CheckUserResponse> checkResponse(){
        CheckUserResponse checkUserResponse = checkUserService.checkUserResponse();
        if(checkUserResponse == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(checkUserResponse);
    }

    @GetMapping("/post")
    public ResponseEntity<PostsResponse> postsResponse(){
        PostsResponse postsResponse = postsService.checkPosts();
        if(postsResponse == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.ok(postsResponse);
    }
}
