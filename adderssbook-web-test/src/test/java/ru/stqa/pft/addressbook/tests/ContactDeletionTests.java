package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {




  @Test
  public void testContactDeletionTests() {
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Sergey", "Sinitsyn", "sergey", "Saint-P.", "+79533469988", "test@mail.ru", "test1"));
    }
    app.getNavigationHelper().returnToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deletedContact();
    app.getNavigationHelper().returnToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);

  }




}
