package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.collections.common.Common;

public class AddContactToGroupTests extends TestBase {

    @Test
    void canAddContactToGroup() {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "test group", "header", "footer"));
        }


        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("Alex", "Smith"));
        }

        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);

        app.contacts().addContactToGroup(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);

        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        Assertions.assertTrue(newRelated.contains(contact));
    }
}