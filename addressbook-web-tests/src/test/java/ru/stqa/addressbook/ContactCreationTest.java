package ru.stqa.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreationTest() {
        gotoAddNewContact();
        fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        submitContact();
    }
}
