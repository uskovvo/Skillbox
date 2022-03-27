package main.model.otherEntities;

import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class CaptchaCodes {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @NonNull @Column(nullable = false)
    private Date time;

    @NonNull @Column(nullable = false, columnDefinition = "TEXT", length = 8)
    private String code;

    @NonNull @Column(nullable = false, columnDefinition = "TEXT", length = 8)
    private String secretCode;
}
