package main.controller;

import main.api.response.PostsResponse;
import main.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class ApiPostController {
    private final PostsService postsService;

    public ApiPostController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/post")
    public ResponseEntity<PostsResponse> postsResponse(@RequestParam(defaultValue = "0") Integer offset,
                                                       @RequestParam(defaultValue = "10") Integer limit,
                                                       @RequestParam(defaultValue = "recent") String mode){

        PostsResponse postsResponse = postsService.checkPosts();
        if(postsResponse == null){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.ok(postsResponse);
    }
}
