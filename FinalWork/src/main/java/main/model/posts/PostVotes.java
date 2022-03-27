package main.model.posts;

import lombok.*;
import main.model.users.Users;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data @NoArgsConstructor @Entity
public class PostVotes {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") @NonNull
    private Users userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id") @NonNull
    private Posts postId;

    @NonNull @Column(nullable = false)
    private Date time;

    @NonNull @Column(nullable = false, columnDefinition = "smallint")
    private byte value;
}
