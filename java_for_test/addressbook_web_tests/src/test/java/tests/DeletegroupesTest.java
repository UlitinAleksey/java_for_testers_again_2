package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletegroupesTest extends TestBase {



    @Test
    public void canRemoveGroup() {

        if (app.groups().getCount()  == 0){
            app.groups().createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1,newGroupCount);

    }


    @Test
    void carRemmoveAllGroupsAtOnce(){
        if (app.groups().getCount()  == 0){
            app.groups().createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.groups().getCount());
    }

}
