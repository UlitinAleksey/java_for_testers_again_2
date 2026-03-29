package tests;

import manager.ApplicationManager;
import model.ContactData;
import org.junit.jupiter.api.Test;

public class DeletecontactTest extends TestBase{

    @Test
    public void candeletecontact() {
        if (!app.isContactPresent()){
            ApplicationManager.createContact(new ContactData("Alexey", "Ulitin", "Ilich",""));

        }

        ApplicationManager.removeContact();

    }


}
