package manager;

import com.mysql.cj.xdevapi.SessionFactory;
import model.GroupData;

import javax.security.auth.login.Configuration;
import java.util.List;

public class HibarnateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibarnateHelper(ApplicationManager manager) {
        super(manager);


       sessionFactory = new Configuration()
                        //.addAnnotatedClass(Book.class)
                        //.addAnnotatedClass(Author.class)
                        // PostgreSQL
                        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook")
                        // Credentials
                        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                        // Create a new SessionFactory
                        .buildSessionFactory();
    }

    public List<GroupData> getGroupList() {
        sessionFactory.fromSession(session -> {
            return  session.createQuery("").List();
                })
    }
}
