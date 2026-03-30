package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class DeletecontactTest extends TestBase{

    @Test
    public void candeletecontact() {
        if (!app.contacts().isContactPresent(app)){
            app.contacts().createContact(new ContactData("Alexey", "Ulitin", "Ilich",""));

        }

        app.contacts().removeContact();

    }


}
