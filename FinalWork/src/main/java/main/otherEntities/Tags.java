package main.otherEntities;

import lombok.*;
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
}
