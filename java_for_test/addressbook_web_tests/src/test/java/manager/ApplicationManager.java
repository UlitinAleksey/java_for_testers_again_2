package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ApplicationManager {
    public static WebDriver driver;

    private LoginHelper session;
    private GroupHelper groups;

    public static void createContact(ContactData contact) {
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        driver.findElement(By.name("middlename")).sendKeys(contact.middlename());
        driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        driver.findElement(By.name("nickname")).sendKeys("lonustv");
        driver.findElement(By.name("company")).sendKeys("alfa");
        driver.findElement(By.name("mobile")).sendKeys("89397180498");
        driver.findElement(By.name("work")).sendKeys("work");
        driver.findElement(By.name("home")).sendKeys("home");
        driver.findElement(By.name("address")).sendKeys(contact.address());
        driver.findElement(By.name("title")).sendKeys("Title");
        driver.findElement(By.name("email")).sendKeys("Mail");
        driver.findElement(By.name("email2")).sendKeys("Mail2");
        driver.findElement(By.name("email3")).sendKeys("Mail3");
        driver.findElement(By.name("homepage")).sendKeys("Homepage");


        {
            WebElement dropdown = driver.findElement(By.name("bday"));
            dropdown.findElement(By.xpath("//option[. = '21']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.name("bmonth"));
            dropdown.findElement(By.xpath("//option[. = 'June']")).click();
        }
        driver.findElement(By.name("byear")).sendKeys("1999");


        {
            WebElement dropdown = driver.findElement(By.name("aday"));
            dropdown.findElement(By.xpath("//option[. = '21']")).click();
        }
        {
            WebElement dropdown = driver.findElement(By.name("amonth"));
            dropdown.findElement(By.xpath("//option[. = 'June']")).click();
        }
        driver.findElement(By.name("ayear")).sendKeys("1999");


        driver.findElement(By.name("submit")).click();


        driver.findElement(By.linkText("home")).click();
    }

    public static void removeContact() {
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.name("delete")).click();
        driver.findElement(By.linkText("home")).click();
    }

    public  void init() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(1936, 1056));
            session().login("admin", "secret");
        }
    }

    public LoginHelper session() {
      if (session == null)  {
          session = new LoginHelper(this);
      }
      return session;
}

    public GroupHelper groups(){
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException exception){
            return false;
        }

    }

    public boolean isContactPresent() {
        return isElementPresent(By.name("selected[]"));
    }
}
