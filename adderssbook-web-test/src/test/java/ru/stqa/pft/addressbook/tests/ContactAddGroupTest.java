package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactAddGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().goToHome();
      app.contact().create(new ContactData()
              .withFirstname("Sergey").withLastname("Sinitsyn").withNickname("sergey")
              .withAddress("Saint-P.").withMobilePhone("+79533469988").withEmail("test@mail.ru"));
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }

  @Test
  public void testContactAddGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();

    Contacts contactBefore = app.db().group(new GroupData().withId(group.getId())).getContacts();
    Groups groupBefore = app.db().contact(new ContactData().withId(contact.getId())).getGroups();

    app.goTo().toHome();
    app.contact().addToGroup(contact,group);
    app.goTo().goToGroupPage();

    Groups groupAfter = app.db().contact(new ContactData().withId(contact.getId())).getGroups();
    Contacts contactAfter = app.db().group(new GroupData().withId(group.getId())).getContacts();

    assertEquals(groupAfter.size(), groupBefore.size() + 1);
    assertThat(groupAfter, equalTo(groupBefore.withAdded(group)));

    assertThat(contactAfter.size(), equalTo(contactBefore.size() + 1));
    assertThat(contactAfter, equalTo(contactBefore.withAdded(contact)));

    verifyContactListInUI();
  }
}
