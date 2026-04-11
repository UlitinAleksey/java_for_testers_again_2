package manager;

import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void openGroupsPage() {
        if (! manager.isElementPresent(By.name("new"))){
            click(By.linkText("groups"));
        }
    }



    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    private void initGroupCreation() {
        click(By.name("new"));
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectFirstGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectFirstGroup(group);
        initGroupModification();
        clearAndFillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }


    private void clearAndFillGroupForm(GroupData group) {
        clearAndType(By.name("group_name"), group.name());
        clearAndType(By.name("group_header"), group.header());
        clearAndType(By.name("group_footer"), group.footer());
    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).sendKeys(text);
    }


    private void clearAndType(By locator, String text) {
        var element = manager.driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }


    private void selectFirstGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));

    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
    }

    private void selectAllGroups() {
        var checkboxes =  manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        for (var span : spans) {
            var name = span.getText();
            var checkbox = span.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }
}