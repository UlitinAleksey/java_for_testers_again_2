import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

class CreatecontactTest  {
    private static WebDriver driver;

    @BeforeEach
    public void setUp() {
        if (driver == null) {
            driver = new FirefoxDriver();
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get("http://localhost/addressbook/");
            driver.manage().window().setSize(new Dimension(550, 694));
            driver.findElement(By.name("user")).click();
            driver.findElement(By.id("LoginForm")).click();
            driver.findElement(By.id("LoginForm")).click();
            driver.findElement(By.name("pass")).click();
            driver.findElement(By.name("pass")).click();
            driver.findElement(By.id("LoginForm")).click();
            driver.findElement(By.name("user")).click();
            driver.findElement(By.name("user")).sendKeys("admin");
            driver.findElement(By.id("LoginForm")).click();
            driver.findElement(By.name("pass")).click();
            driver.findElement(By.name("pass")).sendKeys("secret");
            driver.findElement(By.cssSelector("input:nth-child(7)")).click();
        }
    }

    @Test
    public void createcontact1() {
        driver.findElement(By.linkText("add new")).click();

        driver.findElement(By.name("firstname")).sendKeys("Alexey");
        driver.findElement(By.name("middlename")).sendKeys("Ulitin");
        driver.findElement(By.name("lastname")).sendKeys("Ilich");
        driver.findElement(By.name("nickname")).sendKeys("lonustv");
        driver.findElement(By.name("company")).sendKeys("alfa");
        driver.findElement(By.name("mobile")).sendKeys("89397180498");
        driver.findElement(By.name("work")).sendKeys("work");
        driver.findElement(By.name("home")).sendKeys("home");
        driver.findElement(By.name("address")).sendKeys("Address");
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

    @Test
    public void createcontact1withEmptyname() {

        driver.findElement(By.linkText("add new")).click();

        driver.findElement(By.name("firstname")).sendKeys("");
        driver.findElement(By.name("middlename")).sendKeys("");
        driver.findElement(By.name("lastname")).sendKeys("");
        driver.findElement(By.name("nickname")).sendKeys("lonustv");
        driver.findElement(By.name("company")).sendKeys("alfa");
        driver.findElement(By.name("mobile")).sendKeys("89397180498");
        driver.findElement(By.name("work")).sendKeys("work");
        driver.findElement(By.name("home")).sendKeys("home");
        driver.findElement(By.name("address")).sendKeys("Address");
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
}