package main.posts;

import lombok.*;
import main.users.Users;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor @Entity
public class PostComments {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PostComments parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") @NonNull
    private Posts postId;

    @ManyToOne @JoinColumn(name = "user_id")
    @NonNull
    private Users userId;

    @NonNull @Column(nullable = false)
    private Date time;

    @NonNull @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
}
