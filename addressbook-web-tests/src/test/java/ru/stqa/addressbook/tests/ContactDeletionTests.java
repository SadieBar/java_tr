package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{
  @Test
  public void contactDeletion() throws InterruptedException {
    //if (!app.getContactHelper().isContactPresent()) {
    if (app.db().contacts().size()==0) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withMobilePhone("+79151111111")
              .withEmail("iivanov@mail.ru").withAddress("abc123").withWorkPhone("111").withHomePhone("222")
              .withEmail2("pp@gmail.ru").withEmail3(("agv@test.ru"))

      );
    }
    Contacts before = app.db().contacts();//app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    //int before = app.getContactHelper().getContactCount();
    app.getContactHelper().clickSelectedById(deletedContact);
    app.getContactHelper().clickDelete();
    Thread.sleep(500);//вынуждена поставить задержку, а то тест не проходил
    //int after = app.getContactHelper().getContactCount();
    Contacts after = app.db().contacts();//app.getContactHelper().all();
    assertEquals(after.size(), before.size() - 1);
    //before.remove(before.size()-1);
    //before.remove(deletedContact);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
