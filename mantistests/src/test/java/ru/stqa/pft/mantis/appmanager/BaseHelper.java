package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;

import java.io.File;

public class BaseHelper {
  protected ApplicationManager app;
  protected WebDriver wd;

  public BaseHelper(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
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

  protected void type(By locator, String text) {
    //((JavascriptExecutor) wd).executeScript("arguments[0].scrollIntoView(true);", locator);
    //window.getComputedStyle($('app-element /deep/ #filter-form /deep/ input#txtSearch'))
    clickByLocator(locator);
    if (text!=null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existingText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void attach(By group_name_locator, File file) {
    wd.findElement(group_name_locator).sendKeys(file.getAbsolutePath());
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
