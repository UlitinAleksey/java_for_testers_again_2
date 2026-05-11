package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.Common;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser() {
        var username = Common.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesCli().addUser(email, password);
        app.session().startRegistration(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        Assertions.assertTrue(matcher.find());

        var confirmationUrl = text.substring(matcher.start(), matcher.end());
        app.session().finishRegistration(confirmationUrl, username, password);  // передаем username

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());


        //создать пользователя (адрес вместо "%s") на почтовом сервере (создание пользователя при помощи JamesHelper)
        // Открываем браузер и заполняем форму создания
        //ждем почту ( с помощью MailHelper)
          //      извлечь ссылку из письма
         //       проходим по ссылке из письма и завершаем регистрацию (в браузере)
        // проверяем что пользовательь может залогиниться (с помощью HttpSessionHelper)

    }


    @Test
    void canRegisterUserApi() {
        var username = Common.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesApi().addUser(email, password);
        app.session().startRegistration(username, email);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());

        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        Assertions.assertTrue(matcher.find());

        var confirmationUrl = text.substring(matcher.start(), matcher.end());
        app.session().finishRegistration(confirmationUrl, username, password);

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());


    }
    @Test
    void canRegisterUserWithApi() {
        var username = Common.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";

        app.jamesApi().addUser(email, password);

        app.rest().startRegistration(username, email);


        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());


        var url = Common.extractUrl(messages.get(0).content());
        app.session().finishRegistration(url, password, username);

        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
