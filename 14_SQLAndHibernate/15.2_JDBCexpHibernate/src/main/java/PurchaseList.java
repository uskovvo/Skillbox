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

    @EmbeddedId
    private CompositeKey id;

    @Getter
    @Setter
    @JoinColumn(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Getter
    @Setter
    @Column(name = "course_name")
    private String courseName;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @JoinColumn(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class CompositeKey implements Serializable {

        @Getter
        @Setter
        @Column(name = "student_id")
        private String studentName;

        @Getter
        @Setter
        @Column(name = "subscription_date")
        private Date subscriptionDate;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompositeKey that = (CompositeKey) o;
            return Objects.equals(studentName, that.studentName) && Objects.equals(subscriptionDate, that.subscriptionDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentName, subscriptionDate);
        }
    }
}
