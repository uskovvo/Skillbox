package main.controller;

import main.api.response.PostsResponse;
import main.api.response.TagResponse;
import main.service.TagsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiTagController {

    private final TagsService tagsService;

    public ApiTagController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping("/tag")
    public ResponseEntity<TagResponse> postsResponse(@RequestParam(defaultValue = "") String query){
        TagResponse tagResponse = tagsService.checkTag();
        if(tagResponse == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.ok(tagResponse);
    }
}
