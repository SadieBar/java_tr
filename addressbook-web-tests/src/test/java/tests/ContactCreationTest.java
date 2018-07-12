package tests;

import org.testng.annotations.Test;
import model.ContactData;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreationTest() {
        app.gotoAddNewContact();
        app.fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        app.submitContact();
    }
}
