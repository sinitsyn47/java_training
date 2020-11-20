package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @Test
  public void testContactPhone(){
    app.goTo().toHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFormEdit = app.contact().infoFormEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEdit)));
    assertThat(contact.getAddress(), equalTo(contactInfoFormEdit.getAddress()));
    assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFormEdit)));

  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTest ::cleaned)
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned (String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
