import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker EXCEPTIONS = MarkerManager.getMarker("EXCEPTIONS");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.YYYY");

    public static void main(String[] args) {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();



            Teacher teacher = session.get(Teacher.class, 10);
            System.out.println("Имя преподователя: " + teacher.getName() + "\n\n" + "Какие курсы ведет: ");
            teacher.getCourses().forEach(course -> {
                System.out.println("\n\t" + course.getName() +
                        ", стоимость курса: " + course.getPrice() +
                        ", продолжительность курса: " + course.getDuration() + " мес." +
                        ", студентов на курсе: " + course.getStudentsCount() +"." +
                        "\n\t\tИмена студентов:");
                course.getStudents().forEach(student -> {
                    Subscription subscription = session.get(Subscription.class, new Subscription.CompositeKey(student.getId(), course.getId()));
                    System.out.println("\t\t\t" + student.getName() + ", дата подписки: " + SIMPLE_DATE_FORMAT.format(subscription.getSubscriptionDate()));
                });
            });

            transaction.commit();
            session.close();

        }catch (Exception ex){
            LOGGER.error(EXCEPTIONS, "Внимание ошибка: {}", ex);
        }

    }
}
