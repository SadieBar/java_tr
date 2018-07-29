package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreationTest() {
        //app.getContactHelper().gotoAddNewContact();
        //app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        //app.getContactHelper().submitContact();
        //int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(contact);
        //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
        Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }
}
