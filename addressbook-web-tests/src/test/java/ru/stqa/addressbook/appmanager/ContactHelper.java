package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }
  public void submitContact() {
    clickByLocator(By.name("theform"));
    clickByLocator(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void gotoAddNewContact() {
    clickByLocator(By.cssSelector("html"));
    clickByLocator(By.linkText("add new"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("lastname"),contactData.getSurname());
    type(By.name("nickname"),contactData.getNick());
    type(By.name("mobile"),contactData.getPhone());
    type(By.name("email"),contactData.getEmail());
  }

}
