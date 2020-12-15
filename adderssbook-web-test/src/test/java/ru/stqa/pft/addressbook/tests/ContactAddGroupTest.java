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

    if((app.db().groups().size() == 0) ){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }


  @Test
  public void testContactAddGroup() {

    ContactData contact = selectContact();
    GroupData group = selectGroup(contact);

    Groups groupBefore = app.db().contact(contact.getId()).getGroups();
    Contacts contactBefore = app.db().group(group.getId()).getContacts();

    app.goTo().toHome();
    app.contact().addToGroup(contact.getId(),group.getId());
    app.goTo().goToGroupPage();

    Groups groupAfter = app.db().contact(contact.getId()).getGroups();
    Contacts contactAfter = app.db().group(group.getId()).getContacts();

    assertEquals(groupAfter.size(), groupBefore.size() + 1);
    assertThat(groupAfter, equalTo(groupBefore.withAdded(app.db().group(group.getId()))));

    assertEquals(contactAfter.size(), contactBefore.size() + 1);
    assertThat(contactAfter, equalTo(contactBefore.withAdded(app.db().contact(contact.getId()))));

  }

  private ContactData selectContact() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() < groups.size()) {
        return contact;
      }
    }
    app.goTo().goToHome();
    app.contact().create(new ContactData()
            .withFirstname("Sergey").withLastname("Sinitsyn").withNickname("sergey")
            .withAddress("Saint-P.").withMobilePhone("+79533469988").withEmail("test@mail.ru"));
    Contacts contactAdd = app.db().contacts();
    return app.db().contact(contactAdd.stream().mapToInt((c) -> c.getId()).max().getAsInt());
  }


  private GroupData selectGroup(ContactData contact) {
    Groups groupAll = app.db().groups();
    Groups contactGroups = app.db().contact(contact.getId()).getGroups();
    for (GroupData group : contactGroups) {
      groupAll.remove(group);
    }
    return groupAll.iterator().next();
  }
}
