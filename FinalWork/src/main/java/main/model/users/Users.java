package main.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @NonNull @Column(nullable = false,
            length = 1, columnDefinition = "smallint")
    @JsonIgnore
    private int isModerator;

    @JsonIgnore
    @NonNull @Column(nullable = false)
    private Date regTime;

    @NonNull @Column(nullable = false)
    private String name;

    @NonNull @Column(nullable = false)
    private String email;

    @JsonIgnore
    @NonNull @Column(nullable = false)
    private String password;

    @JsonIgnore
    private String code;

    @Column(columnDefinition = "TEXT")
    private String photo;

    @Transient
    private boolean moderation;

    @Transient
    private long moderationCount;
}
