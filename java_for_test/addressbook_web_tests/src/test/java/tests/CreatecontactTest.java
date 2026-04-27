package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.collections.common.Common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class CreatecontactTest extends TestBase {
    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        var mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>(){});
        result.addAll(value);
        return result;
    }

//    public static List<ContactData> contactProvider() {
//        var result = new ArrayList<ContactData>();
//
//        for (var firstname : List.of("", "Alexey", "Ilich")) {
//            for (var middlename : List.of("", "Ulitin", "Ilich")) {
//                for (var lastname : List.of("", "Ulitin", "Alexey")) {
//                    result.add(new ContactData(firstname, middlename, lastname));
//                }
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//            result.add(new ContactData(
//                    Common.randomString(i * 3 + 1),
//                    Common.randomString(i * 3 + 1),
//                    Common.randomString(i * 3 + 1)
//            ));
//        }
//
//        return result;
//    }
    @Test
    void canCreateContact() {
        var oldContacts = app.hbm().getContactList();
        var contact = new ContactData()
                .withFirstname(Common.randomString(10))
                .withLastname(Common.randomString(10));
                    //    .withPhoto(randomFile("src/test/resources/images"));
        //не работает, проблема с картинкой (не создается контакт)
        app.contacts().create(contact);
        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(oldContacts.size() + 1, newContacts.size());

    }


    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstname(Common.randomString(10))
                .withLastname(Common.randomString(10));
        //    .withPhoto(randomFile("src/test/resources/images"));
        //не работает, проблема с картинкой (не создается контакт)
        if (app.hbm().getGroupCount()  == 0){
            app.hbm().createGroup(new GroupData("", "test gr 2", "test head 2", "test foot 2"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().create(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().create(contact);
        var newContacts = app.hbm().getContactList();

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
                new ContactData("Alexey'", "Last"),
                new ContactData(Common.randomString(15), Common.randomString(15)),
                new ContactData("", "")
        ));
        return result;
    }



}