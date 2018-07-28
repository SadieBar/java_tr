package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class BaseHelper {
  protected WebDriver wd;

  public BaseHelper(WebDriver wd) {
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
    WebElement elm = wd.findElement(locator);
    //elm.click();
    //почему-то у меня не работает в браузере Хром обычный клик, сделала через JS
    JavascriptExecutor executor = (JavascriptExecutor)wd;
    executor.executeScript("arguments[0].click();", elm);
  }

  protected void type(By group_name_locator, String groupName) {
    //((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView(true);", group_name_locator);
    //window.getComputedStyle($('app-element /deep/ #filter-form /deep/ input#txtSearch'))
    clickByLocator(group_name_locator);
    wd.findElement(group_name_locator).clear();
    wd.findElement(group_name_locator).sendKeys(groupName);
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
