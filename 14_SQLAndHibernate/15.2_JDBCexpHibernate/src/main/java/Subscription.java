import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @Getter
    @Setter
    @EmbeddedId
    private  CompositeKey id;

    @Getter
    @Setter
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @Getter
    @Setter
    @JoinColumn(name ="course_id", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class CompositeKey implements Serializable {

        @Getter
        @Setter
        @Column(name = "student_id")
        private int studentId;

        @Getter
        @Setter
        @Column(name = "course_id")
        private int courseId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return studentId == that.studentId && courseId == that.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
