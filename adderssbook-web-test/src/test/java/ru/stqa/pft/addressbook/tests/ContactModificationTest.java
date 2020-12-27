package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;



import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().goToHome();
      app.contact().create(new ContactData()
              .withFirstname("Sergey").withLastname("Sinitsyn").withNickname("sergey")
              .withAddress("Saint-P.").withMobilePhone("+79533469988").withEmail("test@mail.ru"));
    }
  }

  @Test
  public void testContractModification (){
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Sergey2").withLastname("Sinitsyn2").withNickname("sergey47")
            .withAddress("Saint-P., st.Korney-CHukovskogo., h.12").withMobilePhone("+79533469988").withEmail("test@mail.ru").withEmail2("123_test@test.com").withEmail3("qwerty@asdf.ru");
    app.contact().modify(contact);
    app.goTo().goToHome();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(app.db().contact(contact.getId()))));
    verifyContactListInUI();

  }



}
