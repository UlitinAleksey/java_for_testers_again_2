package tests;

import manager.ApplicationManager;
import model.ContactData;
import org.junit.jupiter.api.Test;

class CreatecontactTest extends TestBase {




    @Test
    public void createcontact1() {
        ApplicationManager.createContact(new ContactData("Alexey", "Ulitin", "Ilich",""));
    }

    @Test
    public void createcontact1withEmptyName() {

        ApplicationManager.createContact(new ContactData());
    }

    @Test
    public void createcontact1withNameOnly() {
        var emptyContact = new ContactData();
        var contactwithfirstname = emptyContact.withfirstname("Dmitriy");
        ApplicationManager.createContact(contactwithfirstname);
    }
}