package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

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

  public String getAddress() {
    return wd.findElement(By.name("address")).getText();
  }

  public String getEmail() {
    return wd.findElement(By.name("email")).getAttribute("value");
  }

  public String getHomePhone() {
    return wd.findElement(By.name("home")).getAttribute("value");
  }

  public String getMobilePhone() {
    return wd.findElement(By.name("mobile")).getAttribute("value");
  }

  public String getWorkPhone() {
    return wd.findElement(By.name("work")).getAttribute("value");
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("lastname"), contactData.getSurname());
    type(By.name("nickname"), contactData.getNick());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());
  }

  public void clickEdit(int index) {
    List<WebElement> elms = wd.findElements(By.xpath("//img[@title=\"Edit\"]"));
    elms.get(index).click();
    //clickByLocator(By.xpath("//img[@title=\"Edit\"]"));
  }

  public void clickEditById(ContactData modifiedData) {
    String xpath = "//a[@href='edit.php?id="+modifiedData.getId()+"']";
    wd.findElement(By.xpath(xpath)).click();
  }

  public void clickUpdate() {
    clickByLocator(By.xpath("//input[@type=\"submit\"]"));
  }

  public void clickSelectedById(int index) {
    List<WebElement> data = wd.findElements(By.xpath("//input[@name=\"selected[]\"]"));
    data.get(index).click();
    //clickByLocator(By.xpath("//input[@name=\"selected[]\"]"));
  }

  public void clickSelectedById(ContactData deletedContact) {
    WebElement data = wd.findElement(By.cssSelector("input[id='"+deletedContact.getId()+"']"));
    data.click();
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


  /*public List<ContactData> getContactList() {
    List<ContactData> datas = new ArrayList<>();
    //не работает строчка ниже
    List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr"));
    for (int i = 0; i < elements.size() - 1; i++) {
      String path = "//tbody/tr[" + (i + 2) + "]";
      String surname = wd.findElement(By.xpath(path + "/td[2]")).getText();
      String name = wd.findElement(By.xpath(path + "/td[3]")).getText();
      String ids = wd.findElement(By.xpath(path + "/td[7]/a")).getAttribute("href");
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
  }*/

  public Contacts all() {
    Contacts datas = new Contacts();
    //не работает строчка ниже
    List<WebElement> elements = wd.findElements(By.xpath("//tbody/tr"));
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String allphones = cells.get(5).getText();
      //String[] phones = cells.get(5).getText().split("\n");
      datas.add(new ContactData().withId(id).withName(firstname).withSurname(lastname)
              .withAllPhones(allphones));
    }
    /*for (int i = 0; i < elements.size() - 1; i++) {
      String path = "//tbody/tr[" + (i + 2) + "]";
      String surname = wd.findElement(By.xpath(path + "/td[2]")).getText();
      String name = wd.findElement(By.xpath(path + "/td[3]")).getText();
      String ids = wd.findElement(By.xpath(path + "/td[7]/a")).getAttribute("href");
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
    }*/
    return datas;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname)
            .withSurname(lastname).withMobilePhone(mobile)
            .withHomePhone(home).withWorkPhone(work);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("//input[@value='#s']/../../td[8]/a",id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='#s']]/td[8]/a",id))).click();
    //wd.findElement((By.cssSelector((String.format("a[href='edit.php?id=%s'",id))))).click();
  }
}