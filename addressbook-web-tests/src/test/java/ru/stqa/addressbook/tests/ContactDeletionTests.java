package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{
  @Test
  public void contactDeletion(){
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withPhone("+79151111111")
              .withEmail("iivanov@mail.ru"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    //int before = app.getContactHelper().getContactCount();
    app.getContactHelper().clickSelected(before.size()-1);
    app.getContactHelper().clickDelete();
    //int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size()-1);
    Assert.assertEquals(before, after);
  }
}
