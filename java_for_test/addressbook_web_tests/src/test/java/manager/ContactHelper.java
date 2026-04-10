package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public void removeContact() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("home")).click();
    }

    public boolean isContactPresent(ApplicationManager manager) {
        return manager.isElementPresent(By.name("selected[]"));
    }
}
