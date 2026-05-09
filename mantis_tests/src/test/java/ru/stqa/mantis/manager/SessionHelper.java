package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.cssSelector("input[type='submit']"));
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
    }

    public boolean isLoggedIn() {
        return isElementPresent(By.cssSelector("span.user-info"));
    }

    public void startRegistration(String username, String email) {
        click(By.linkText("Signup for a new account"));
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[type='submit']"));
    }

    public void finishRegistration(String confirmationUrl, String username, String password) {
        manager.driver().get(confirmationUrl);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isElementPresent(By.name("realname"))) {
            type(By.name("realname"), username);
        }
        if (isElementPresent(By.name("password"))) {
            type(By.name("password"), password);
            type(By.name("password_confirm"), password);
        }

        click(By.xpath("//span[text()='Update User']/parent::button"));
    }
}