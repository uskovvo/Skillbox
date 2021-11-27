import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        Course course = session.get(Course.class, 9);
        System.out.println("Название курса: " + course.getName() + "\n" +
                           "Сколько человек учится на курсе: " + course.getStudentsCount() + "\n");

        Student student = session.get(Student.class, 4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
        System.out.println("Имя студента: " + student.getName() + "\n" +
                           "Возраст студента: " + student.getAge() + "\n" +
                           "Дата регистрации: " + simpleDateFormat.format(student.getRegistrationDate()) + "\n");

        Teacher teacher = session.get(Teacher.class, 11);
        System.out.println("Имя преподователя: " + teacher.getName() + "\n" +
                           "Возраст преподователя: " + teacher.getAge() + "\n" +
                           "Зарплата преподователя: " + teacher.getSalary() + "\n");
    }
}
