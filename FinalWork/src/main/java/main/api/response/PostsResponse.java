package main.api.response;

import lombok.Data;
import main.model.posts.Posts;

import java.util.List;

@Data
public class PostsResponse {
    private long count;
    private List<Posts> posts;
}
