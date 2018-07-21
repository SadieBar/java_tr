package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{
  @Test
  public void contactDeletion(){
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
    }
    app.getContactHelper().clickSelected();
    app.getContactHelper().clickDelete();
  }
}
