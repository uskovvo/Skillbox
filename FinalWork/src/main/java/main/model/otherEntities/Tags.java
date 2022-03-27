package main.model.otherEntities;

import lombok.*;
import main.model.posts.Posts;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Tags {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @NonNull @Column(nullable = false)
    private String name;

    @OneToOne(optional = false, mappedBy = "tag")
    @JoinColumn(name = "post_id")
    private Posts post;
}
