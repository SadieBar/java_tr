package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
    }
    app.getContactHelper().clickEdit();
    app.getContactHelper().fillContactForm(new ContactData("Peter","Petrov", "pp", "+79152222222", "ppetrov@mail.ru"));
    app.getContactHelper().clickUpdate();
  }
}
