package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.DbHelper;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  @Test
  public void changePassword() throws IOException, MessagingException {

    List<UserData> users = new DbHelper().users();
    int index = 0;
    do{
      index = (int) new Random().nextInt(users.size());
    } while (users.get(index).getUser().equals("administrator"));
    String email = users.get(index).getEmail();//"user1535483269767@localhost";
    String username = users.get(index).getUser();//"user1535481726385";
    String password = "password";
    List<MailMessage> mailMessagesBefore = app.james().waitForMail(username,password,60000);

    app.changepass().start("administrator","root");
    app.changepass().clickUsersManage();
    app.changepass().clickUser(username);
    app.changepass().resetPassword();
    List<MailMessage> mailMessagesAfter;
    do {
      mailMessagesAfter = app.james().waitForMail(username, password, 60000);
    } while (mailMessagesBefore.size()==mailMessagesAfter.size());
    //MailMessage latestMessage = mailMessages.get(mailMessages.size()-1);
    //List<MailMessage> newList = new ArrayList<>(); newList.add(latestMessage);
    String passchangeLink = app.changepass().findChangePasswordLink(mailMessagesAfter, email);
    String newPassword = "password1";
    app.changepass().finish(passchangeLink,newPassword);

    HttpSession session = app.newSession();

    assertTrue(session.login(username, newPassword));
    assertTrue(session.isLoggedInAs(username));
  }
}
