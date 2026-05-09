package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать пользователя (адрес вместо "%s") на почтовом сервере (создание пользователя при помощи JamesHelper)
        // Открываем браузер и заполняем форму создания
        //ждем почту ( с помощью MailHelper)
          //      извлечь ссылку из письма
         //       проходим по ссылке из письма и завершаем регистрацию (в браузере)
        // проверяем что пользовательь может залогиниться (с помощью HttpSessionHelper)

    }
}
