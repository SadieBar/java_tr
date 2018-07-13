package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreationTest() {
        app.getContactHelper().gotoAddNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        app.getContactHelper().submitContact();
    }
}
