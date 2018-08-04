package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  public void clickEdit(int index) {
    List<WebElement> elms = wd.findElements(By.xpath("//img[@title=\"Edit\"]"));
    elms.get(index).click();
    //clickByLocator(By.xpath("//img[@title=\"Edit\"]"));
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
    //не работает строчка ниже
    List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr"));
    for (int i=0;i<elements.size()-1;i++) {
      String path = "//tbody/tr["+(i+2)+"]";
      String surname = wd.findElement(By.xpath(path+"/td[2]")).getText();
      String name = wd.findElement(By.xpath(path+"/td[3]")).getText();
      String ids = wd.findElement(By.xpath(path+"/td[7]/a")).getAttribute("href");
      String pattern = ".*id=(\\d+)";
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(ids);
      int id = 0;
      if (m.find()) {
        id = Integer.parseInt(m.group(1));
      } else {
        System.out.println("Error matching!");
      }
      ContactData data = new ContactData().withName(name).withSurname(surname).withId(id);
      datas.add(data);
    }
    return datas;
  }
}
