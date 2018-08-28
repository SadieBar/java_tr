package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChangePasswordHelper extends BaseHelper {
  public ChangePasswordHelper(ApplicationManager applicationManager) {
    super(applicationManager);
  }
  public void clickUsersManage(){
    clickByLocator(By.xpath("//span[text()=' управление ']"));
    clickByLocator(By.xpath("//a[text()='Управление пользователями']"));
  }
  public void start(String username, String password) {
    wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
    type(By.name("username"),username);
    clickByLocator(By.cssSelector("input[value=Войти]"));
    type(By.name("password"),password);
    clickByLocator(By.xpath("//input[@type='submit']"));//в моей версии поле называется так
  }

  public void clickUser(String user) {
    String xpath = "//a[text()='"+user+"']";
    //WebElement elm = this.app.getDriver().findElement(By.xpath(xpath));
    //((JavascriptExecutor)app.getDriver()).executeScript("arguments[0].scrollIntoView();",elm);
    clickByLocator(By.xpath(xpath));
  }

  public void resetPassword() {
    clickByLocator(By.cssSelector("input[value='Сбросить пароль']"));
  }

  public String findResetLink(List<MailMessage> mailMessages, String email){
    MailMessage message = mailMessages.stream().filter((m)->m.to.equals("Someone (presumably you) requested a password ")).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(message.text);
  }

  public String findChangePasswordLink(List<MailMessage> mailMessages, String email){
    //List<MailMessage> newList = new ArrayList<>();
    //newList.add(mailMessages.get(mailMessages.size()-1));
    //MailMessage message = newList.stream().filter((m)->m.to.equals("Someone (presumably you) requested a password change")).findFirst().get();
    ArrayList<Long> datesList = new ArrayList<>();
    for (int i=0;i<mailMessages.size();i++) {
      datesList.add(mailMessages.get(i).messageDate.getTime());
    }
    long max = Collections.max(datesList);
    int latestIndex = 0;
    for (int i=0;i<mailMessages.size();i++) {
      if (mailMessages.get(i).messageDate.getTime()==max) {
        latestIndex = i;
        break;
      }
    }


    MailMessage message = mailMessages.get(latestIndex);
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(message.text);
  }

  public void finish(String link, String pass) {
    wd.get(link);
    type(By.name("password"),pass);
    type(By.name("password_confirm"),pass);
    clickByLocator(By.xpath("//span[text() = 'Изменить учетную запись']"));//в моей версии поле называется так
  }
}
