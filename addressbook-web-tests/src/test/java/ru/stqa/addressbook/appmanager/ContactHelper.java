package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
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

  public void clickEdit() {
    clickByLocator(By.xpath("//img[@title=\"Edit\"]"));
  }

  public void clickUpdate() {
    clickByLocator(By.xpath("//input[@type=\"submit\"]"));
  }

  public void clickSelected() {
    clickByLocator(By.xpath("//input[@name=\"selected[]\"]"));
  }

  public void clickDelete() {
    clickByLocator(By.xpath("//input[@value=\"Delete\"]"));
    acceptAlert();
  }
  public void createContact(ContactData data) {
    gotoAddNewContact();
    fillContactForm(data);
    submitContact();
  }

  public boolean isContactPresent() {
    //почему-то у меня в Хром поменялось имя на selected[]
    return isElementPresent(By.name("selected[]"));
  }

  private void acceptAlert() {
    wd.switchTo().alert().accept();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
