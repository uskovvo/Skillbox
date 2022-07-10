package main.service;

import main.api.response.TagResponse;
import main.model.otherEntities.Tags;
import main.model.posts.Posts;
import main.repository.PostsRepository;
import main.repository.TagsRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

@Service
public class TagsService {
    private int count;
    private int countPostTag;
    private final PostsRepository postsRepository;
    private final TagsRepository tagsRepository;
    private TagResponse tagResponse;
    private final List<Tags> tagsList = new ArrayList<>();
    private final Map<Integer, Integer> countsTag = new HashMap<>();

    public TagsService(PostsRepository postsRepository, TagsRepository tagsRepository) {
        this.postsRepository = postsRepository;
        this.tagsRepository = tagsRepository;
    }

    public TagResponse checkTag() {
        getTags();
        return tagResponse;
    }

    private void getTags() {
        tagResponse = new TagResponse();
        int weights = (int) postsRepository.count();
        Iterable<Posts> allPosts = postsRepository.findAll();
        Iterable<Tags> allTags = tagsRepository.findAll();
        int maxCountTag = findMaxCountInMap(allTags, allPosts);
        if (maxCountTag != 0) {
            allTags.forEach(tag -> {
                for (Map.Entry<Integer, Integer> entry : countsTag.entrySet()) {
                    if (countsTag.containsKey(tag.getId())) {
                        int countTag = entry.getValue();
                        tag.setWeight(getWeight(weights, countTag, maxCountTag));
                    }
                }
                tagsList.add(tag);
            });
        }
        tagResponse.setTags(tagsList);
    }

    private int findMaxCountInMap(Iterable<Tags> allTags, Iterable<Posts> allPosts) {
        allTags.forEach(tag -> {
            count = 0;
            countPostTag = getCountPostTag(tag.getName(), allPosts);
            countsTag.put(tag.getId(), countPostTag);
        });
        if(countsTag.isEmpty()){
            return 0;
        }
        return Collections.max(countsTag.values());
    }

    private int getCountPostTag(String tagName, Iterable<Posts> allPosts) {
        allPosts.forEach(post -> {
            if (post.getTag().getName().equals(tagName)) {
                count++;
            }
        });
        return count;
    }

    private Double getWeight(int weights, int countPostTag, int maxCount) {
        DecimalFormat dF = new DecimalFormat("#.##");
        double dWeightTag = countPostTag / weights;
        double dWeightMax = maxCount / weights;
        double k = 1 / dWeightMax;
        return Double.parseDouble(dF.format(dWeightTag * k));
    }
}
