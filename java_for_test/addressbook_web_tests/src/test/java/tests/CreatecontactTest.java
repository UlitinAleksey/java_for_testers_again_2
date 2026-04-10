package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

class CreatecontactTest extends TestBase {


    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();

        for (var firstname : List.of("", "Alexey", "Ilich")) {
            for (var middlename : List.of("", "Ulitin", "Ilich")) {
                for (var lastname : List.of("", "Ulitin", "Alexey")) {
                    result.add(new ContactData(firstname, middlename, lastname));
                }
            }
        }


        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(
                    randomString(i * 5 + 1),
                    randomString(i * 5 + 1),
                    randomString(i * 5 + 1)
            ));
        }

        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        app.contacts().createContact(contact);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("Alexey'", "Middle", "Last"),
                new ContactData(randomString(200), randomString(200), randomString(200)),
                new ContactData("", "", "")
        ));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        app.contacts().createContact(contact);
    }

}