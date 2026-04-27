package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper {
    private final ApplicationManager manager;

    public ContactHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void create(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void initContactCreation() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("nickname"), "lonustv");
        type(By.name("company"), "alfa");
        type(By.name("mobile"), "89397180498");
        type(By.name("work"), "work");
        type(By.name("home"), "home");
        type(By.name("title"), "Title");
        type(By.name("email"), "Mail");
        type(By.name("email2"), "Mail2");
        type(By.name("email3"), "Mail3");
        type(By.name("homepage"), "Homepage");

        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '21']")).click();
        }
        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'June']")).click();
        }
        type(By.name("byear"), "1999");

        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '21']")).click();
        }
        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'June']")).click();
        }
        type(By.name("ayear"), "1999");
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void removeContact(ContactData contact) {
        selectContact(contact);
        click(By.name("delete"));
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    private void openHomePage() {
        if (!manager.isElementPresent(By.name("searchform"))) {
            click(By.linkText("home"));
        }
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//tr[@name='entry']"));
        for (var row : rows) {
            var checkbox = row.findElement(By.cssSelector("input[type='checkbox']"));
            var id = checkbox.getAttribute("value");
            var lastname = row.findElement(By.xpath(".//td[2]")).getText();
            var firstname = row.findElement(By.xpath(".//td[3]")).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void click(By locator) {
        manager.driver.findElement(locator).click();
    }

    private void type(By locator, String text) {
        var element = manager.driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    private void clearAndType(By locator, String text) {
        var element = manager.driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        click(By.id("MassCB"));
        click(By.name("delete"));
        click(By.linkText("home"));
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        clickEditButtonForContact(contact);
        clearAndFillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void clickEditButtonForContact(ContactData contact) {
        manager.driver.findElement(By.xpath(String.format("//input[@value='%s']/ancestor::tr//img[@alt='Edit']", contact.id()))).click();
    }

    private void clearAndFillContactForm(ContactData contact) {
        clearAndType(By.name("firstname"), contact.firstname());
        clearAndType(By.name("lastname"), contact.lastname());
    }

    private void submitContactModification() {
        click(By.name("update"));
    }
}