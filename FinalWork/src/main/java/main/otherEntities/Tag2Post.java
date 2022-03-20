package main.otherEntities;

import lombok.*;
import main.posts.Posts;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Tag2Post {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "post_id")
    @NonNull
    private Posts postId;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "tag_id")
    @NonNull
    private Tags tagId;
}
