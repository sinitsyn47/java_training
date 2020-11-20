package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Set;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().toHome();
    Set<ContactData> before = app.contact().all();
    app.contact().initContactCreation();
    ContactData contact = new ContactData()
            .withFirstname("Sergey1").withLastname("Sinitsyn").withNickname("sergey")
            .withAddress("Saint-P.").withMobile("+79533469988").withEmail("test@mail.ru").withGroup("test1");
    app.contact().create(contact);
    app.goTo().goToHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);

  }


}
