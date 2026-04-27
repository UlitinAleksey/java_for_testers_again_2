package manager;

import model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AvailableSettings;

import java.util.List;

public class HibarnateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibarnateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupData.class)  // Добавьте вашу сущность
                // .addAnnotatedClass(ContactData.class)  // Добавьте другие сущности
                .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/addressbook")
                .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                .setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                .setProperty(AvailableSettings.SHOW_SQL, "true")  // для отладки
                .buildSessionFactory();
    }

    public List<GroupData> getGroupList() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM GroupData", GroupData.class).list();
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}