package manager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ISelect;

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

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        click(By.id("header"));
        click(By.linkText("groups"));
        click(By.id("footer"));
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

    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        click(By.name("selected[]"));
        removeSelectedGroup();
        returnToGroupsPage();

    }

    private void removeSelectedGroup() {
        click(By.name("delete"));
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
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
        click(By.name("group_header"));
        click(By.name("group_header"));
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());

    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).sendKeys(text);
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectGroup() {
        click(By.linkText("groups"));
        click(By.name("selected[]"));
    }
}
