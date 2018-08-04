package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {
//@Test(enabled=false)
    @Test
    public void contactCreationTest() {
        //app.getContactHelper().gotoAddNewContact();
        //app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        //app.getContactHelper().submitContact();
        //int before = app.getContactHelper().getContactCount();
        Set<ContactData> before = app.getContactHelper().all();
        ContactData contact = new ContactData()
                .withName("Ivan").withSurname("Ivanov").withNick("ii")
                .withPhone("+79151111111").withEmail("iivanov@mail.ru");
        app.getContactHelper().createContact(contact);
        Set<ContactData> after = app.getContactHelper().all();
        //int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() + 1);

        //contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
        Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(ById);
        //after.sort(ById);
        Assert.assertEquals(before, after);
    }
}
