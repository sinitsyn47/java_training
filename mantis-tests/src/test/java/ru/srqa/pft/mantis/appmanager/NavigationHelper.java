package ru.srqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;



public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void settings() {
    wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    click(By.cssSelector("button[type='button']"));
    click(By.linkText("Управление"));

  }

  public void manageUsers() {
    wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    click(By.linkText("Управление пользователями"));

  }

}
