package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseHelper {
  protected FirefoxDriver wd;

  public BaseHelper(FirefoxDriver wd) {
    this.wd = wd;
  }
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
  public void clickByLocator(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By group_name_locator, String groupName) {
    clickByLocator(group_name_locator);
    wd.findElement(group_name_locator).clear();
    wd.findElement(group_name_locator).sendKeys(groupName);
  }
}
