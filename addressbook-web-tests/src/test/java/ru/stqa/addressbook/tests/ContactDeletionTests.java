package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Set;

public class ContactDeletionTests extends TestBase{
  @Test
  public void contactDeletion(){
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withPhone("+79151111111")
              .withEmail("iivanov@mail.ru"));
    }
    Set<ContactData> before = app.getContactHelper().all();
    ContactData deletedContact = before.iterator().next();
    //int before = app.getContactHelper().getContactCount();
    app.getContactHelper().clickSelectedById(deletedContact);
    app.getContactHelper().clickDelete();
    //int after = app.getContactHelper().getContactCount();
    Set<ContactData> after = app.getContactHelper().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    //before.remove(before.size()-1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
