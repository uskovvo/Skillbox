package main.otherEntities;

import lombok.*;
import org.hibernate.type.YesNoType;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class GlobalSettings {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NonNull @Column(nullable = false)
    private int id;

    @NonNull @Column(nullable = false)
    private String code;

    @NonNull @Column(nullable = false)
    private String name;

    @NonNull @Column(nullable = false)
    private YesNoType value;
}
