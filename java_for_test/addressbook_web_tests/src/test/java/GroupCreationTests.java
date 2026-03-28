import model.GroupData;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));


    }

    @Test
  public void CanCreateGroupWithEmptyName() {

        openGroupsPage();
        createGroup(new GroupData());


    }

    @Test
    public void CanCreateGroupWithNameOnly() {

        openGroupsPage();
        var emptyGroup = new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);


    }

}
