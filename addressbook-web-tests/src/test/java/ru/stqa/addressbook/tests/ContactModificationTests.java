package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    app.getContactHelper().clickEdit();
    app.getContactHelper().fillContactForm(new ContactData("Peter","Petrov", "pp", "+79152222222", "ppetrov@mail.ru"));
    app.getContactHelper().clickUpdate();
  }
}
