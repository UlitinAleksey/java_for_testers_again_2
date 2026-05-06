package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {


    @Test
    void testContactInfo() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);

        String expected = contact.address() + "\n" +
                contact.home() + "\n" +
                contact.mobile() + "\n" +
                contact.work() + "\n" +
                contact.allEmails();

        String actual = app.contacts().getAddress(contact) + "\n" +
                app.contacts().getPhones(contact) + "\n" +
                app.contacts().getEmails(contact);

        Assertions.assertEquals(expected, actual);
    }
}
