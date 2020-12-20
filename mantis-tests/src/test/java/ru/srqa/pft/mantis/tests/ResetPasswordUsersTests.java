package ru.srqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.srqa.pft.mantis.model.MailMessage;
import ru.srqa.pft.mantis.model.UserData;
import ru.srqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordUsersTests extends TestBase {

  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testResetPasswordUsers() throws IOException, MessagingException {
    Users users = app.db().users();
    UserData user = users.iterator().next();
    String userName = user.getName();
    String userEmail = user.getEmail();
    String newPassword = "qwerty123";
    app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().settings();
    app.goTo().manageUsers();
    app.user().selectById(user.getId());
    app.user().passwordReset();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, userEmail);
    app.registration().finish(confirmationLink, userName,newPassword);
    assertTrue(app.newSession().login(userName, newPassword));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);

  }


  @AfterMethod(alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
