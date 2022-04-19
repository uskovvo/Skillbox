package main.service;

import main.api.response.CheckUserResponse;
import main.model.posts.ModerationStatus;
import main.model.posts.Posts;
import main.model.users.Users;
import main.repository.CheckUserRepository;
import main.repository.PostsRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CheckUserService {
    private final CheckUserRepository checkUserRepository;
    private final PostsRepository postsRepository;

    public CheckUserService(CheckUserRepository repository, PostsRepository postsRepository) {
        this.checkUserRepository = repository;
        this.postsRepository = postsRepository;
        checkData();
    }

    private void checkData(){
        if(checkUserRepository.count() < 1){
            Users admin = new Users();
            admin.setId(1);
            admin.setIsModerator(1);
            admin.setRegTime(new Date());
            admin.setName("Valeriy");
            admin.setEmail("uskovvospb@gmail.com");
            admin.setPassword("163425");
            admin.setPhoto("/avatars/ab/cd/ef/1.jpg");
        }
    }

    public CheckUserResponse checkUserResponse(){
        CheckUserResponse response = new CheckUserResponse();
        response.setResult(true);
        if(response.isResult()) {
            Users user = new Users();
            user.setId(576);
            user.setName("Федор Емельяненко");
            user.setPhoto("/avatars/ab/cd/ef/52461.jpg");
            user.setEmail("mail@mail.ru");
            user.setIsModerator(0);
            user.setModerationCount(checkIsUserModerator(user));
            response.setUser(user);
            return response;
        }
        return response;
    }

    private long checkIsUserModerator(Users user){
        final long[] countValue = {0};
        if(user.getIsModerator() == 1) {
            Iterable<Posts> posts = postsRepository.findAll();
            posts.forEach(post -> {
                if (post.getModerationStatus().equals(ModerationStatus.NEW)) {
                    countValue[0]++;
                }
            });
            user.setModeration(true);
            return countValue[0];
        }
        return 0;
    }
}
