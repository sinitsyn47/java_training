package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    } else {

      click(By.linkText("groups"));
    }
  }

  public void goToHome() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home page"));
    }
  }

  public void toHome(){
    click(By.linkText("home"));
  }

  public void goToGroupPage(){
    click(By.xpath("//div[@id='content']/div/i/a"));
  }

}
