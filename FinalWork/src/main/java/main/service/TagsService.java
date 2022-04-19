package main.service;

import main.repository.PostsRepository;
import main.repository.TagsRepository;

public class TagsService {
    private String query;
    private String weight;
    private final PostsRepository postsRepository;
    private final TagsRepository tagsRepository;

    public TagsService(PostsRepository postsRepository, TagsRepository tagsRepository) {
        this.postsRepository = postsRepository;
        this.tagsRepository = tagsRepository;
    }
}
