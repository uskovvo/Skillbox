import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                                                .configure("hibernate.cfg.xml")
                                                .build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Course course = session.get(Course.class, 5);
        System.out.println(course.getName() + " - " + course.getStudentsCount());

        Teacher teacher = session.get(Teacher.class, 2);
        System.out.println(teacher.getName() + " - " + teacher.getAge());

        Student student = session.get(Student.class, 5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
        System.out.println(student.getName() + " - " + simpleDateFormat.format(student.getRegistrationDate()));

        sessionFactory.close();
    }
}
