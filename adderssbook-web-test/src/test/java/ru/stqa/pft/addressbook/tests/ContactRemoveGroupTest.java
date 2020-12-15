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

public class ContactRemoveGroupTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.goTo().goToHome();
      app.contact().create(new ContactData()
              .withFirstname("Sergey").withLastname("Sinitsyn").withNickname("sergey")
              .withAddress("Saint-P.").withMobilePhone("+79533469988").withEmail("test@mail.ru"));
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
      app.goTo().toHome();
    }
  }

  @Test
  public void testContactRemoveGroup() {

    GroupData group = selectGroup();
    Contacts contactBefore = app.db().group(group.getId()).getContacts();

    ContactData contact = contactBefore.iterator().next();
    Groups groupBefore = app.db().contact(contact.getId()).getGroups();

    app.goTo().toHome();
    app.contact().removeFromGroup(contact.getId(),group.getId());
    app.goTo().goToGroupPage();

    Contacts contactAfter = app.db().group(group.getId()).getContacts();
    Groups groupAfter = app.db().contact(contact.getId()).getGroups();

    assertEquals(groupAfter.size(), groupBefore.size() - 1);
    assertThat(groupAfter, equalTo(groupBefore.without(app.db().group(group.getId()))));

    assertEquals(contactAfter.size(), contactBefore.size() - 1);
    assertThat(contactAfter, equalTo(contactBefore.without(app.db().contact(contact.getId()))));

    verifyContactListInUI();
  }

  private GroupData selectGroup() {
    Groups groups = app.db().groups();
    for (GroupData group : groups) {
      if (app.db().group(group.getId()).getContacts().size() > 0) {
        return group;
      }
    }
    GroupData groupTest = groups.iterator().next();
    ContactData contacts = app.db().contacts().iterator().next();
    app.goTo().toHome();
    app.contact().addToGroup(contacts.getId(), app.db().groups().iterator().next().getId());
    return groupTest;
  }


}
