package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void initGroupCreation() {
    clickByLocator(By.name("new"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getGroupName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void deleteSelectedGroups() {
    clickByLocator(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //clickByLocator(By.name("selected[]"));
  }
  public void initGroupModification(){
    clickByLocator(By.name("edit"));
  }

  public void submitGroupModification() {
    clickByLocator(By.name("update"));
  }

  public void create(GroupData groupData) {
    initGroupCreation();
    fillGroupForm(groupData);
    clickByLocator(By.name("submit"));
  }

  public boolean isGroupPresent() {
    //почему-то у меня в Хроме поменялось имя элемента на selected[]
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elms = wd.findElements(By.cssSelector("span.group"));
    for (WebElement elm: elms) {
      String name = elm.getText();
      int id = Integer.parseInt(elm.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withGroupName(name).withId(id));
    }
    return groups;
  }

  public void modify(int index, GroupData newdata) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(newdata);
    submitGroupModification();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroups();
  }


}
