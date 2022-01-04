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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final Marker EXCEPTIONS = MarkerManager.getMarker("EXCEPTIONS");
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.YYYY");

    public static void main(String[] args) {
        try {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                                                    configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

//            expHibernate(session);
//            queryBuilder(session);
            hql(session);
//            createNewTable(session);

            transaction.commit();
            session.close();

        }catch (Exception ex){
            LOGGER.error(EXCEPTIONS, "Внимание ошибка: {}", ex);
        }

    }

    private static void expHibernate (Session session){
        Teacher teacher = session.get(Teacher.class, 10);
        System.out.println("Имя преподователя: " + teacher.getName() + "\n\n" + "Какие курсы ведет: ");
        teacher.getCourses().forEach(course -> {
            System.out.println("\n\t" + course.getName() +
                    ", стоимость курса: " + course.getPrice() +
                    ", продолжительность курса: " + course.getDuration() + " мес." +
                    ", студентов на курсе: " + course.getStudentsCount() +"." +
                    "\n\t\tИмена студентов:");
            course.getStudents().forEach(student -> {
                Subscription subscription = session.get(Subscription.class,
                        new Subscription.CompositeKey(student.getId(), course.getId()));
                PurchaseList purchaseList = session.get(PurchaseList.class,
                        new PurchaseList.CompositeKey(student.getName(), course.getName()));
                System.out.println("\t\t\t" + student.getName() + ", дата подписки: " +
                        SIMPLE_DATE_FORMAT.format(subscription.getSubscriptionDate()) +
                        ", стоимость курса " + purchaseList.getCourse().getName());

            });
        });
    }

    private static void queryBuilder(Session session){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = criteriaBuilder.createQuery(Course.class);
        Root<Course> root =query.from(Course.class);
        query.select(root).where(criteriaBuilder.greaterThan(root.get("price"), 100000)).
                orderBy(criteriaBuilder.asc(root.get("price")));
        List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();

        courseList.forEach(course -> System.out.println(course.getName() +
                " - " + course.getTeacher().getName() +
                " - " + course.getPrice() +
                "\n"));
    }

    private static void hql(Session session){
        String s = "price";

        String hql = "From " + Course.class.getSimpleName() + " where " + s + " > 120000" + " order by " + s;
        List<Course> courseListHql = session.createQuery(hql).getResultList();
        System.out.println(courseListHql.size());
        courseListHql.forEach(course -> System.out.println(course.getName() + " - " + course.getPrice()));
    }

    private static void createNewTable(Session session){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> query = criteriaBuilder.createQuery(PurchaseList.class);
        Root<PurchaseList> root = query.from(PurchaseList.class);
        query.select(root);
        List<PurchaseList> purchaseLists = session.createQuery(query).getResultList();
        purchaseLists.forEach(purchaseList -> {
            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList.setId(new LinkedPurchaseList.LinkedPurchaseListKey(purchaseList.getStudent().getId(),
                    purchaseList.getCourse().getId()));
            linkedPurchaseList.setStudentName(purchaseList.getStudent().getName());
            linkedPurchaseList.setCourseName(purchaseList.getCourse().getName());
            linkedPurchaseList.setSubscriptionDate(purchaseList.getSubscriptionDate());

            session.saveOrUpdate(linkedPurchaseList);
        });
    }
}
