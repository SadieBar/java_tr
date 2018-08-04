package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withPhone("+79151111111")
              .withEmail("iivanov@mail.ru"));
    }
    //int before = app.getContactHelper().getContactCount();
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size()-1;
    app.getContactHelper().clickEdit(index);
    ContactData newdata = new ContactData().withName("Peter")
            .withSurname("Petrov").withNick("pp").withPhone("+79152222222")
            .withEmail("ppetrov@mail.ru");
    app.getContactHelper().fillContactForm(newdata);
    app.getContactHelper().clickUpdate();
    //int after = app.getContactHelper().getContactCount();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    newdata.withId(before.get(index).getId());
    before.remove(index);
    before.add(newdata);
    Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);

  }
}
