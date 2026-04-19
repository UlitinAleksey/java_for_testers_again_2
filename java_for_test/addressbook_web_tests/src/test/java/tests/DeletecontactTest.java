package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class DeletecontactTest extends TestBase{

    @Test
    public void candeletecontact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().create(new ContactData("Alexey", "Ulitin", "Ilich"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);

    }

    @Test
    public void canDeleteAllContacts() {
        if (app.contacts().getCount() == 0) {
            app.contacts().create(new ContactData("Default", "", "Contact"));
        }

        app.contacts().removeAllContacts();
        var newContacts = app.contacts().getList();

        Assertions.assertEquals(0, newContacts.size());
    }


}
