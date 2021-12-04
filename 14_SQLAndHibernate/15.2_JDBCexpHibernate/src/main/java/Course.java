import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Courses")
public class Course implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int duration;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    @Getter
    @Setter
    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @Column(name = "price_per_hour")
    private Float pricePerHour;

    @Getter
    @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
                joinColumns = {@JoinColumn(name = "course_id")},
                inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;
}
