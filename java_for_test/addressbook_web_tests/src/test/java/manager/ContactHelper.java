package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper {
    private final ApplicationManager manager;

    public ContactHelper(ApplicationManager manager){
        this.manager = manager;
    }

    public void createContact(ContactData contact) {
        manager.driver.findElement(By.linkText("add new")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("nickname")).sendKeys("lonustv");
        manager.driver.findElement(By.name("company")).sendKeys("alfa");
        manager.driver.findElement(By.name("mobile")).sendKeys("89397180498");
        manager.driver.findElement(By.name("work")).sendKeys("work");
        manager.driver.findElement(By.name("home")).sendKeys("home");
        manager.driver.findElement(By.name("title")).sendKeys("Title");
        manager.driver.findElement(By.name("email")).sendKeys("Mail");
        manager.driver.findElement(By.name("email2")).sendKeys("Mail2");
        manager.driver.findElement(By.name("email3")).sendKeys("Mail3");
        manager.driver.findElement(By.name("homepage")).sendKeys("Homepage");

        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '21']")).click();
        }
        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'June']")).click();
        }
        manager.driver.findElement(By.name("byear")).sendKeys("1999");

        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '21']")).click();
        }
        {
            WebElement dropdown = ApplicationManager.driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'June']")).click();
        }
        manager.driver.findElement(By.name("ayear")).sendKeys("1999");

        manager.driver.findElement(By.name("submit")).click();
        manager.driver.findElement(By.linkText("home")).click();
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

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
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
    private void initContactModification() {
        click(By.cssSelector("img[alt='Edit']"));
    }
    private void clickEditButtonForContact(ContactData contact) {
        manager.driver.findElement(By.xpath(String.format("//input[@value='%s']/ancestor::tr//img[@alt='Edit']", contact.id()))).click();
    }

    private void clearAndFillContactForm(ContactData contact) {
        clearAndType(By.name("firstname"), contact.firstname());
        clearAndType(By.name("middlename"), contact.middlename());
        clearAndType(By.name("lastname"), contact.lastname());
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }
    private void clearAndType(By locator, String text) {
        var element = manager.driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }
}