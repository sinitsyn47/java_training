package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("Sergey2").withLastname("Sinitsyn2").withNickname("sergey47")
            .withAddress("Saint-P.").withMobile("+79533469988").withEmail("test@mail.ru");
    app.contact().modify(contact);
    app.goTo().goToHome();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());


    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
