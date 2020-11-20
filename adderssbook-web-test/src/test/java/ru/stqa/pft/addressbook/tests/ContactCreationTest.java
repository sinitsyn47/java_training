package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


  @Test
  public void testContactCreation() {
    app.goTo().toHome();
    Contacts before = app.contact().all();
    app.contact().initContactCreation();
    ContactData contact = new ContactData()
            .withFirstname("Sergey1").withLastname("Sinitsyn").withNickname("sergey")
            .withAddress("Saint-P.").withMobile("+79533469988").withEmail("test@mail.ru").withGroup("test1");
    app.contact().create(contact);
    app.goTo().goToHome();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));

  }


}
