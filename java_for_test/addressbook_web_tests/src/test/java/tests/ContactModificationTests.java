package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().create(new ContactData("1", "", "2"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData()
                .withFirstname("modified firstname")
                .withMiddlename("modified middlename")
                .withLastname("modified lastname");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        var expectedContact = new ContactData()
                .withId(oldContacts.get(index).id())
                .withFirstname(testData.firstname())
                .withLastname(testData.lastname());
        expectedList.set(index, expectedContact);
        Comparator<ContactData> compareById = (o1, o2) ->
                Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }
}