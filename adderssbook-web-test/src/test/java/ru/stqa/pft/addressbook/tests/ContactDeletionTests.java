package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {




  @Test
  public void testContactDeletionTests() {
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Sergey", "Sinitsyn", "sergey", "Saint-P.", "+79533469988", "test@mail.ru", "test1"));
    }
    app.getNavigationHelper().returnToHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deletedContact();
  }




}
