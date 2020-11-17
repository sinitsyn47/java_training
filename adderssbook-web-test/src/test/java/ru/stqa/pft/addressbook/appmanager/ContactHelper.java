package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());


    if (creation) {
      if (!wd.findElement(By.name("new_group")).equals(ContactData.getGroup())) {
        new Select(wd.findElement(By.name("new_group"))).selectByValue("[none]");
      } else if (wd.findElement(By.name("new_group")).equals(ContactData.getGroup())) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(ContactData.getGroup());
      } else Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();

  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    WebElement tbl = wd.findElement(By.xpath("//*[@id=\"maintable\"]/tbody"));
    List<WebElement> elements = tbl.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      for (int i = 0; i < cells.size(); i= + 10) {
        int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
        String lastname = cells.get(1).getText();
        String firstname = cells.get(2).getText();
        ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null, null);
        contacts.add(contact);
      }
    }
    return contacts;
  }
}

