package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {
  @Test
  public void changePassword() throws IOException, MessagingException {

    String email = "user1535481726385@localhost";
    String username = "user1535481726385";String password = "password";

    app.changepass().start("administrator","root");
    app.changepass().clickUsersManage();
    app.changepass().clickUser(username);
    app.changepass().resetPassword();
    List<MailMessage> mailMessages = app.james().waitForMail(username,password,60000);
    //MailMessage latestMessage = mailMessages.get(mailMessages.size()-1);
    //List<MailMessage> newList = new ArrayList<>(); newList.add(latestMessage);
    String passchangeLink = app.changepass().findChangePasswordLink(mailMessages, email);
    String newPassword = "password1";
    app.changepass().finish(passchangeLink,newPassword);

    HttpSession session = app.newSession();

    assertTrue(session.login(username, newPassword));
    assertTrue(session.isLoggedInAs(username));
  }
}
