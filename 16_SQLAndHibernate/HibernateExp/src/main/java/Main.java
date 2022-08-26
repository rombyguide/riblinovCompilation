import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

//        Course course = session.get(Course.class, 2);
//        System.out.println(course.getName().concat(": ") + course.getStudentsCount() + " студентов");
//
//        Teachers teachers = session.get(Teachers.class, 3);
//        System.out.println("Преподаватель: " + teachers.getName() + "\nВозраст: " + teachers.getAge() +
//                " лет\n" + "З/п - " + teachers.getSalary() + " руб.");

        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        Student student = session.get(Student.class, 2);
//        List<Student> students = course.getStudents();
//        students.forEach(student -> System.out.println(student.getName()));


        Subscription subscription = session.get(Subscription.class,
                new KeySubscription(student.getId(), course.getId()));
        System.out.println(subscription.getStudent().getName());

        transaction.commit();
        sessionFactory.close();
    }
}