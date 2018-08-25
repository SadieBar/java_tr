package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroup extends TestBase {
  @Test
  public void removeContactFromGroup() throws InterruptedException {
    if (app.db().contacts().size()==0){
      app.goTo().homePage();
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withMobilePhone("+79151111111")
              .withEmail("iivanov@mail.ru").withAddress("abc123").withWorkPhone("111").withHomePhone("222"));
    }
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      //if (app.group().all().size() == 0)//(!app.group().isGroupPresent()) {
      app.group().create(new GroupData().withGroupName("test1"));
      app.goTo().groupPage();
    }
    Contacts before = app.db().contacts();
    ContactData deleteContactGroup = new ContactData();
    GroupData deleteGroup = new GroupData();
    boolean found = false;
    for ( ContactData contact :  before ) {
      Groups groups = contact.getGroups();
      if (groups.size()!=0) {
        deleteContactGroup = contact;
        deleteGroup = groups.delegate().iterator().next();
        found = true;
        break;
      }
    }
    if (!found) {
      (new ContactToGroupTests()).contactToGroup();
      before = app.db().contacts();
      for ( ContactData contact :  before ) {
        Groups groups = contact.getGroups();
        if (groups.size()!=0) {
          deleteContactGroup = contact;
          deleteGroup = groups.delegate().iterator().next();
          break;
        }
      }
    }
    app.goTo().homePage();
    app.getContactHelper().selectGroup(deleteGroup.getGroupName());
    app.getContactHelper().clickSelectedById(deleteContactGroup);
    app.getContactHelper().removeFromGroup();
    Contacts after = app.db().contacts();
    String aftergroup="";
    Groups groups = new Groups();
    boolean leftDeletedGroup = false;
    for ( ContactData contact :  after ) {
      if (contact.equals(deleteContactGroup))
        groups = contact.getGroups();
      for (GroupData group: groups) {
        if (group.getGroupName().equals(deleteGroup.getGroupName()))
          leftDeletedGroup = true;
      }
    }
    assertThat(leftDeletedGroup, equalTo(false));
  }
}
