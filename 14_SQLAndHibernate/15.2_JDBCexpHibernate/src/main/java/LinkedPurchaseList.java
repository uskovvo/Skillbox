import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "linked_purchase_list")
public class LinkedPurchaseList {

    @Getter
    @Setter
    @EmbeddedId
    private LinkedPurchaseListKey id;

    @Getter
    @Setter
    @Column(name = "student_id", insertable = false, updatable = false)
    private Integer studentId;

    @Getter
    @Setter
    @Column(name = "course_id", insertable = false, updatable = false, nullable = true)
    private Integer courseId;

    @Getter
    @Setter
    @Column(name = "student_name")
    private String studentName;

    @Getter
    @Setter
    @Column(name = "course_name")
    private String courseName;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class LinkedPurchaseListKey implements Serializable{

        @Getter
        @Setter
        @Column(name = "student_id")
        private Integer studentId;

        @Getter
        @Setter
        @Column(name = "course_id", nullable = true)
        private Integer courseId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LinkedPurchaseListKey that = (LinkedPurchaseListKey) o;
            return studentId == that.studentId && courseId == that.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
