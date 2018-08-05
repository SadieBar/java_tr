package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{
  @Test
  public void contactDeletion(){
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withMobilePhone("+79151111111")
              .withEmail("iivanov@mail.ru"));
    }
    Contacts before = app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    //int before = app.getContactHelper().getContactCount();
    app.getContactHelper().clickSelectedById(deletedContact);
    app.getContactHelper().clickDelete();
    //int after = app.getContactHelper().getContactCount();
    Contacts after = app.getContactHelper().all();
    assertEquals(after.size(), before.size() - 1);
    //before.remove(before.size()-1);
    //before.remove(deletedContact);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
