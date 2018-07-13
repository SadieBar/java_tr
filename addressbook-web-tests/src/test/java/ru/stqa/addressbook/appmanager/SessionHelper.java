package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends BaseHelper {

  public SessionHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void login(String userName, String password) {
    clickByLocator(By.cssSelector("html"));
    type(By.name("user"),userName);
    type(By.name("pass"),password);
    clickByLocator(By.xpath("//form[@id='LoginForm']/input[3]"));
  }
}
