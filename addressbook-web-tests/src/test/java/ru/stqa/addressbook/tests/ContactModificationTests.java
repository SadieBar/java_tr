package ru.stqa.addressbook.tests;

import org.apache.commons.logging.LogSource;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    //if (!app.getContactHelper().isContactPresent())
    if (app.db().contacts().size()==0){
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withMobilePhone("+79151111111")
              .withEmail("iivanov@mail.ru").withAddress("abc123").withWorkPhone("111").withHomePhone("222")
              .withEmail2("pp.email@mail.ru").withEmail3("test@mail.ri"))
              ;
    }

    //int before = app.getContactHelper().getContactCount();
    Contacts before = app.db().contacts();//app.getContactHelper().all();
    ContactData modifiedData = before.iterator().next();
    //int index = before.size()-1;
    app.getContactHelper().clickEditById(modifiedData);
    ContactData newdata = new ContactData().withName("Peter")
            .withSurname("Petrov").withNick("pp").withMobilePhone("+79152222222")
            .withEmail("ppetrov@mail.ru").withWorkPhone("123").withHomePhone("321").withAddress("abc321")
            //.withPhoto(new File("addressbook-web-tests\\src\\test\\resources\\stru.png"))
            .withEmail2("pp@gmail.com").withEmail3("peter@gmail.com");

            app.getContactHelper().fillContactForm(newdata);
    app.getContactHelper().clickUpdate();
    //int after = app.getContactHelper().getContactCount();
    Contacts after = app.db().contacts();// app.getContactHelper().all();
    Assert.assertEquals(after.size(), before.size());

    //newdata.withId(modifiedData.getId());
    //before.remove(modifiedData);
    //before.add(newdata);
    //Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(ById);
    //after.sort(ById);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedData)
            .withAdded(newdata.withId(modifiedData.getId()))));
  }
}
