package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.AvailableSettings;

import java.util.ArrayList;
import java.util.List;

public class HibarnateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibarnateHelper(ApplicationManager manager) {
        super(manager);

        sessionFactory = new Configuration()
                .addAnnotatedClass(ContactRecord.class)
                .addAnnotatedClass(GroupRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost:3306/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                .setProperty(AvailableSettings.SHOW_SQL, "true")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records){
        List<GroupData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    static List<ContactData> convertContactList(List<ContactRecord> records){
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId("" + record.id)
                .withFirstname(record.firstname)
                .withLastname(record.lastname)
                .withAddress(record.address);
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        ContactRecord record = new ContactRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.address());
        record.middlename = "";
        return record;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("FROM GroupRecord", GroupRecord.class).list();
        }));
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count(*) FROM GroupRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, Integer.parseInt(group.id())).contacts);
        });
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("FROM ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("SELECT COUNT(*) FROM ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createContact(ContactData contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(contact));
            session.getTransaction().commit();
        });
    }

    public void deleteContact(ContactData contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            var record = session.get(ContactRecord.class, Integer.parseInt(contact.id()));
            session.remove(record);
            session.getTransaction().commit();
        });
    }

    public void modifyContact(ContactData contact) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            var record = session.get(ContactRecord.class, Integer.parseInt(contact.id()));
            record.firstname = contact.firstname();
            record.lastname = contact.lastname();
            record.address = contact.address();
            session.getTransaction().commit();
        });
    }
}