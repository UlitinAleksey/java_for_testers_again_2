package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup () {
        if (!app.groups().isGroupPresent()){
            app.groups().createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));
    }
}
