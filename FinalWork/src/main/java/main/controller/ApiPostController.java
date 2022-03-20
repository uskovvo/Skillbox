package main.controller;

import main.posts.Posts;
import main.repository.PostsRepository;
import main.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiPostController {

    @Autowired
    private PostsRepository postsRepository;

    @PostMapping("/post/*")
    public int add(Posts posts){
        Posts newPosts = postsRepository.save(posts);
        return newPosts.getId();
    }
}
