package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        int groupCount = app.groups().getCount();

        app.groups().createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));

        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1,newGroupCount);

    }

    @Test
  public void CanCreateGroupWithEmptyName() {


        app.groups().createGroup(new GroupData());


    }

    @Test
    public void CanCreateGroupWithNameOnly() {


        app.groups().createGroup(new GroupData().withName("some name"));


    }

}
