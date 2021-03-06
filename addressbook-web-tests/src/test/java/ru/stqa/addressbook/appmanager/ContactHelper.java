package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;

public class ContactHelper extends BaseHelper {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContact() {
    clickByLocator(By.name("theform"));
    clickByLocator(By.xpath("//input[@value=\"Enter\"]"));
  }

  public void gotoAddNewContact() {
    clickByLocator(By.cssSelector("html"));
    clickByLocator(By.linkText("add new"));
  }

  /*private void contactBuild(ContactData contactData) {
    assert (By.name("firstname"),contactData.getName());
    assert (By.name("lastname"),contactData.getSurname());
    assert (By.name("nickname"), contactData.getNick());
    assert (By.name(""), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
  }*/

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
    type(By.name("firstname"),contactData.getName());
    type(By.name("lastname"),contactData.getSurname());
    type(By.name("nickname"), contactData.getNick());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
    //attach(By.name("photo"), contactData.getPhoto());
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

  public String getCurrentGroup() {
    WebElement data = wd.findElement(By.xpath("//select[@name=\"to_group\"]"));
    List<WebElement> elms = data.findElements(By.xpath("./option"));
    return elms.get(0).getText();
  }

  public void clickDelete() {
    clickByLocator(By.xpath("//input[@value=\"Delete\"]"));
    acceptAlert();
  }

  public void clickAddToGroup() {
    clickByLocator(By.xpath("//input[@name=\"add\"]"));
  }

  public void createContact(ContactData data) {
    gotoAddNewContact();
    fillContactForm(data);
    submitContact();
  }

  public void selectGroup(String name) {
    Select elm = new Select(wd.findElement(By.xpath("//select[@name=\"group\"]")));
    elm.selectByVisibleText(name);
  }

  public void removeFromGroup() {
    WebElement elm = wd.findElement(By.xpath("//input[@name='remove']"));
    elm.click();
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
      String allPhones= cells.get(5).getText();
      String emails = cells.get(4).getText();
      String address = cells.get(3).getText();
      //String[] phones = cells.get(5).getText().split("\n");
      datas.add(new ContactData().withId(id).withName(firstname)
              .withSurname(lastname).withAllEmails(emails).withAllPhones(allPhones));//.withAllPhones(allPhones.toString()).withAllEmails(emails.toString()));
    }
    /*for (int i = 0; i < elements.size() - 1; i++) {
      String path = "//tbody/tr[" + (i + 2) + "]";
      String surname = wd.findElement(By.xpath(path + "/td[2]")).getText();
      String name = wd.findElement(By.xpath(path + "/td[3]")).getText();

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
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");


    //wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname)
            .withSurname(lastname).withMobilePhone(mobile)
            .withHomePhone(home).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
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

  public void verifyContact(ContactData contact) {
    int id = -1; int index = -1; int i=0;
    List<WebElement> rows = wd.findElements(By.name("entry"));

    do {
      id = Integer.parseInt(rows.get(i).findElement(By.tagName("input")).getAttribute("value"));
      if (id==contact.getId()) {
        index = i;
        break;
      }
      i++;
    } while (i<rows.size());
    List<WebElement> cells = rows.get(index).findElements(By.tagName("td"));
    String firstname = cells.get(2).getText();
    String lastname = cells.get(1).getText();
    String allPhones= cells.get(5).getText();
    String emails = cells.get(4).getText();
    String address = cells.get(3).getText();
    assertEquals(firstname, contact.getName());
    assertEquals(lastname, contact.getSurname());
    assertEquals(allPhones,contact.getAllphones());
    assertEquals(emails,contact.getEmails());
    assertEquals(address,contact.getAddress());
  }
}