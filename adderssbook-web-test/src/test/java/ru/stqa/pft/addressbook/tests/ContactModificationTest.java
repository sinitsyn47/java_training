package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("Sergey").withLastname("Sinitsyn").withNickname("sergey")
              .withAddress("Saint-P.").withMobile("+79533469988").withEmail("test@mail.ru").withGroup("test1"));
    }
    app.goTo().goToHome();
  }

  @Test
  public void testContractModification (){
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Sergey2").withLastname("Sinitsyn2").withNickname("sergey47")
            .withAddress("Saint-P.").withMobile("+79533469988").withEmail("test@mail.ru");
    app.contact().modify(contact);
    app.goTo().goToHome();
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}
