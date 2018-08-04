package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withPhone("+79151111111")
              .withEmail("iivanov@mail.ru"));
    }
    //int before = app.getContactHelper().getContactCount();
    Set<ContactData> before = app.getContactHelper().all();
    ContactData modifiedData = before.iterator().next();
    //int index = before.size()-1;
    app.getContactHelper().clickEditById(modifiedData);
    ContactData newdata = new ContactData().withName("Peter")
            .withSurname("Petrov").withNick("pp").withPhone("+79152222222")
            .withEmail("ppetrov@mail.ru");
    app.getContactHelper().fillContactForm(newdata);
    app.getContactHelper().clickUpdate();
    //int after = app.getContactHelper().getContactCount();
    Set<ContactData> after = app.getContactHelper().all();
    Assert.assertEquals(after.size(), before.size());

    newdata.withId(modifiedData.getId());
    before.remove(modifiedData);
    before.add(newdata);
    //Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(ById);
    //after.sort(ById);
    Assert.assertEquals(before, after);

  }
}
