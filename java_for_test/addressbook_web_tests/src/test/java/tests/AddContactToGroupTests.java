package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddContactToGroupTests extends TestBase {

    @Test
    void canAddContactToGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "test group", "header", "footer"));
        }

        if (app.contacts().getCount() == 0) {
            app.contacts().create(new ContactData("Alex", "Smith"));
        }

        var groups = app.hbm().getGroupList();
        var contacts = app.hbm().getContactList();

        ContactData selectedContact = null;
        GroupData selectedGroup = null;

        for (var contact : contacts) {
            for (var group : groups) {
                List<ContactData> contactsInGroup = app.hbm().getContactsInGroup(group);
                if (contactsInGroup == null || !contactsInGroup.contains(contact)) {
                    selectedContact = contact;
                    selectedGroup = group;
                    break;
                }
            }
            if (selectedContact != null) break;
        }

        if (selectedContact == null) {
            selectedContact = new ContactData("New", "Contact");
            app.contacts().create(selectedContact);
            selectedGroup = groups.get(0);
        }

        List<ContactData> oldRelated = app.hbm().getContactsInGroup(selectedGroup);
        int oldSize = (oldRelated == null) ? 0 : oldRelated.size();

        app.contacts().addContactToGroup(selectedContact, selectedGroup);

        List<ContactData> newRelated = app.hbm().getContactsInGroup(selectedGroup);
        int newSize = (newRelated == null) ? 0 : newRelated.size();

        Assertions.assertEquals(oldSize + 1, newSize);
        Assertions.assertTrue(newRelated != null && newRelated.contains(selectedContact));
    }
}