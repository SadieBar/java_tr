package ru.stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTests extends TestBase {
  @Test
  public void contactToGroup() throws InterruptedException {
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
    app.goTo().homePage();
    Contacts before = app.db().contacts();//app.getContactHelper().all();
    ContactData modContact = before.iterator().next();
    //int before = app.getContactHelper().getContactCount();
    String curgroup = app.getContactHelper().getCurrentGroup();

    app.getContactHelper().clickSelectedById(modContact);

    app.getContactHelper().clickAddToGroup();

    Thread.sleep(500);//для просмотра теста
    app.goTo().homePage();

    Contacts after = app.db().contacts();
    Groups groups = new Groups();
    String aftergroup="";
    for ( ContactData contact :  after ) {
      if (contact.equals(modContact))
        groups = contact.getGroups();
        for (GroupData group: groups) {
          if (group.getGroupName().equals(curgroup))
            aftergroup = group.getGroupName();
        }
    }
    assertThat(curgroup, equalTo(aftergroup));
  }
}
