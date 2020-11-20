package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().toHome();
    List<ContactData> before = app.contact().list();
    app.contact().initContactCreation();
    ContactData contact = new ContactData("Sergey1", "Sinitsyn", "sergey", "Saint-P.", "+79533469988", "test@mail.ru", "test1");
    app.contact().create(contact);
    app.goTo().goToHome();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
