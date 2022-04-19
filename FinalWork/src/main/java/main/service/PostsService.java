package main.service;

import main.api.response.PostsResponse;
import main.model.posts.ModerationStatus;
import main.model.posts.Posts;
import main.repository.PostsRepository;
import main.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {
    private final PostsRepository postsRepository;
    private final UsersRepository usersRepository;
    private PostsResponse postsResponse;
    private List<Posts> postsList = new ArrayList<>();

    public PostsService(PostsRepository postsRepository, UsersRepository usersRepository) {
        this.postsRepository = postsRepository;
        this.usersRepository = usersRepository;
    }

    public PostsResponse checkPosts(){
        if(postsRepository.count() == 0){
            getAllPosts();
        }
        postsResponse = new PostsResponse();
        postsResponse.setCount(0);
        postsResponse.setPosts(new ArrayList<>());
        return postsResponse;
    }

    private void getAllPosts(){
        postsResponse = new PostsResponse();
        Iterable<Posts> posts = postsRepository.findAll();
        posts.forEach(post -> {
            if(post.getModerationStatus() == ModerationStatus.ACCEPTED) {
                postsList.add(post);
            }
        });
        postsResponse.setPosts(postsList);
    }
}
