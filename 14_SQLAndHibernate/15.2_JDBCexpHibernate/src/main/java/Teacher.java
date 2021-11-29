import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int salary;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
}
