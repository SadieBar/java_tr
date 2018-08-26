package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.tests.TestBase;

public class RegistrationHelper extends BaseHelper{

  public RegistrationHelper(ApplicationManager applicationManager) {
    super(applicationManager);
  }

  public void start(String username, String email) {

    wd.get(app.getProperty("web.baseUrl")+"/signup_page.php");
    type(By.name("username"),username);
    type(By.name("email"),email);
    clickByLocator(By.cssSelector("input[value=Зарегистрироваться]"));//в моей версии поле называется так
  }
  public void finish(String link, String pass) {
    wd.get(link);
    type(By.name("password"),pass);
    type(By.name("password_confirm"),pass);
    clickByLocator(By.xpath("//span[text() = 'Изменить учетную запись']"));//в моей версии поле называется так
  }
}
