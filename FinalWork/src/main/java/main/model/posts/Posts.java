package main.model.posts;

import lombok.*;
import main.model.otherEntities.Tags;
import main.model.users.Users;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Posts {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @NonNull @Column(nullable = false,
            length = 1, columnDefinition = "smallint")
    private byte isActive;

    @Enumerated(EnumType.STRING)
    @Column (nullable = false)
    private ModerationStatus moderationStatus = ModerationStatus.NEW;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "moderator_id")
    private Users moderatorId;

    @NonNull @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_id")
    private Users userId;

    @NonNull @Column(nullable = false)
    private Date time;

    @NonNull @Column(nullable = false)
    private String title;

    @NonNull @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @NonNull @Column(nullable = false)
    private int viewCount;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "tag_id")
    private Tags tag;
}
