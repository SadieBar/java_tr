package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void clickSelected(int index) {
    List<WebElement> data = wd.findElements(By.xpath("//input[@name=\"selected[]\"]"));
    data.get(index).click();
    //clickByLocator(By.xpath("//input[@name=\"selected[]\"]"));
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

  public List<ContactData> getContactList() {
    List<ContactData> datas = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr"));
    for (WebElement element: elements) {
      String surname = element.findElement(By.xpath("//td[2]")).getText();
      String name = element.findElement(By.xpath("//td[3]")).getText();
      ContactData data = new ContactData(name, surname, null, null, null);
      datas.add(data);
    }
    return datas;
  }
}
