package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

class CreatecontactTest extends TestBase {




    @Test
    public void createcontact1() {
        app.contacts().createContact(new ContactData("Alexey", "Ulitin", "Ilich",""));
    }

    @Test
    public void createcontact1withEmptyName() {

        app.contacts().createContact(new ContactData());
    }

    @Test
    public void createcontact1withNameOnly() {
        var emptyContact = new ContactData();
        var contactwithfirstname = emptyContact.withfirstname("Dmitriy");
        app.contacts().createContact(contactwithfirstname);
    }
}