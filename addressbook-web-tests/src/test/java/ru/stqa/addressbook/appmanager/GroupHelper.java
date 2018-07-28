package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.addressbook.model.GroupData;

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

  public void selectGroup() {
    clickByLocator(By.name("selected[]"));
  }
  public void initGroupModification(){
    clickByLocator(By.name("edit"));
  }

  public void submitGroupModification() {
    clickByLocator(By.name("update"));
  }

  public void createGroup(GroupData groupData) {
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
}
