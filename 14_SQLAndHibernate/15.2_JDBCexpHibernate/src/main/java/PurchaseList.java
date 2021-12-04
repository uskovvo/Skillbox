import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "purchaselist")
public class PurchaseList {

    @Getter
    @Setter
    @EmbeddedId
    private CompositeKey id;

    @Getter
    @Setter
    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @Getter
    @Setter
    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Getter
    @Setter
    private int price;

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
        @Column(name = "student_name")
        private String studentName;

        @Getter
        @Setter
        @Column(name = "course_name")
        private String courseName;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return Objects.equals(studentName, that.studentName) && Objects.equals(courseName, that.courseName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentName, courseName);
        }
    }
}
