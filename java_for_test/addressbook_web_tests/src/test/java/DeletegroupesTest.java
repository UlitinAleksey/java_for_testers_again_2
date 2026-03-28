import model.GroupData;
import org.junit.jupiter.api.Test;

public class DeletegroupesTest extends TestBase {



    @Test
    public void canRemoveGroup() {
        openGroupsPage();
        if (!isGroupPresent()){
            createGroup(new GroupData("test gr 2", "test head 2", "test foot 2"));
        }

        removeGroup();


    }

}
