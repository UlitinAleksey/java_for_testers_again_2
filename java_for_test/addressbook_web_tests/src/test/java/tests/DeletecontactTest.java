package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeletecontactTest extends TestBase {

    @Test
    public void candeletecontact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("Alexey", "Ulitin"));

            app.contacts().refreshPage();
        }

        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        app.contacts().removeContact(oldContacts.get(index));

        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);

        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    public void canDeleteAllContacts() {

        app.contacts().openHomePage();

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("Default", "Contact"));
        }

        app.contacts().removeAllContacts();

        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(0, newContacts.size());
    }
}