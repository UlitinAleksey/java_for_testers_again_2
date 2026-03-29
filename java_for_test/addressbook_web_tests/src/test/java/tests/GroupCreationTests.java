package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {

        app.groups().createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));


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
