package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager){
        super(manager);
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

        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }

    public List<GroupData> getList() {
        openGroupsPage();

        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());

    }
}