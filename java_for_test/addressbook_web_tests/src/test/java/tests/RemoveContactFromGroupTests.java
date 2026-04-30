package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RemoveContactFromGroupTests extends TestBase {

    @Test
    void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "test group", "header", "footer"));
        }

        var groups = app.hbm().getGroupList();

        ContactData selectedContact = null;
        GroupData selectedGroup = null;

        for (var group : groups) {
            List<ContactData> contactsInGroup = app.hbm().getContactsInGroup(group);
            if (contactsInGroup != null && !contactsInGroup.isEmpty()) {
                selectedGroup = group;
                selectedContact = contactsInGroup.get(0);
                break;
            }
        }

        if (selectedContact == null) {
            if (app.contacts().getCount() == 0) {
                app.contacts().create(new ContactData("Default", "Contact"));
            }
            selectedGroup = groups.get(0);
            selectedContact = app.hbm().getContactList().get(0);
            app.contacts().addContactToGroup(selectedContact, selectedGroup);
        }

        List<ContactData> oldRelated = app.hbm().getContactsInGroup(selectedGroup);
        int oldSize = (oldRelated == null) ? 0 : oldRelated.size();

        app.contacts().removeContactFromGroup(selectedContact, selectedGroup);

        List<ContactData> newRelated = app.hbm().getContactsInGroup(selectedGroup);
        int newSize = (newRelated == null) ? 0 : newRelated.size();

        Assertions.assertEquals(oldSize - 1, newSize);
        Assertions.assertTrue(newRelated == null || !newRelated.contains(selectedContact));
    }
}