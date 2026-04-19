package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.collections.common.Common;

import java.util.ArrayList;
import java.util.Comparator;
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

        for (int i = 0; i < 3; i++) {
            result.add(new ContactData(
                    Common.randomString(i * 3 + 1),
                    Common.randomString(i * 3 + 1),
                    Common.randomString(i * 3 + 1)
            ));
        }

        return result;
    }
    @Test
    void canCreateContact() {
        var contact = new ContactData()
                .withFirstname(Common.randomString(10))
                .withLastname(Common.randomString(10))
                        .withPhoto(randomFile("src/test/resources/images"));
        //не работает, проблема с картинкой (не создается контакт)
        app.contacts().create(contact);

    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().create(contact);
        var newContacts = app.contacts().getList();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        var lastId = newContacts.get(newContacts.size() - 1).id();
        var contactWithoutMiddlename = new ContactData().withId(lastId).withFirstname(contact.firstname()).withLastname(contact.lastname());
        expectedList.add(contactWithoutMiddlename);
        expectedList.sort(compareById);

        Assertions.assertEquals(expectedList, newContacts);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("Alexey'", "", "Last"),
                new ContactData(Common.randomString(15), "", Common.randomString(15)),
                new ContactData("", "", "")
        ));
        return result;
    }



}