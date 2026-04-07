package manager;

import model.GroupData;
import org.openqa.selenium.By;

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
        returnToGroupsPage();  // Исправлено: используем существующий метод вместо кликов
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
        selectFirstGroup();  // Исправлено: выбираем первую группу
        removeSelectedGroups();
        returnToGroupsPage();
    }

    private void removeSelectedGroups() {
        click(By.name("delete"));
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectFirstGroup();  // Исправлено: выбираем первую группу
        initGroupModification();
        clearAndFillGroupForm(modifiedGroup);  // Исправлено: очищаем поля перед заполнением
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

    // Новый метод для очистки полей перед заполнением при редактировании
    private void clearAndFillGroupForm(GroupData group) {
        clearAndType(By.name("group_name"), group.name());
        clearAndType(By.name("group_header"), group.header());
        clearAndType(By.name("group_footer"), group.footer());
    }

    private void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).sendKeys(text);
    }

    // Новый метод: очищает поле и вводит текст
    private void clearAndType(By locator, String text) {
        var element = manager.driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    // ИСПРАВЛЕНО: выбираем первую группу с помощью чекбокса
    private void selectFirstGroup() {
        // Находим все чекбоксы групп
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        if (!checkboxes.isEmpty()) {
            checkboxes.get(0).click();  // Кликаем по первому чекбоксу
        }
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
}