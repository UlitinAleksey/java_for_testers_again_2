package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.collections.common.Common;

public class RemoveContactFromGroupTests extends TestBase {

    @Test
    void canRemoveContactFromGroup() {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "test group", "header", "footer"));
        }


        var group = app.hbm().getGroupList().get(0);

        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("Default", "Contact"));
        }

        var contact = app.hbm().getContactList().get(0);


        app.contacts().addContactToGroup(contact, group);

        var oldRelated = app.hbm().getContactsInGroup(group);


        app.contacts().removeContactFromGroup(contact, group);

        var newRelated = app.hbm().getContactsInGroup(group);

        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
        Assertions.assertFalse(newRelated.contains(contact));
    }
}