package ru.stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {
    @DataProvider
    public Iterator<Object[]> velidContactsFromXml() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("addressbook-web-tests/src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            ;
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }
//@Test(enabled=false)
    @Test(dataProvider = "velidContactsFromXml")
    public void contactCreationTest(ContactData contact) {
        //app.getContactHelper().gotoAddNewContact();
        //app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        //app.getContactHelper().submitContact();
        //int before = app.getContactHelper().getContactCount();
        Contacts before = app.getContactHelper().all();
        //File photofile = new File("addressbook-web-tests/src/test/resources/stru.png");
        //ContactData contact = new ContactData()
        //        .withName("Ivan").withSurname("Ivanov").withNick("ii")
        //        .withMobilePhone("+79151111111").withWorkPhone("222").withHomePhone("111").withEmail("iivanov@mail.ru").withAddress("abc123").withPhoto(photofile);
        app.getContactHelper().createContact(contact);
        Contacts after = app.getContactHelper().all();
        //int after = app.getContactHelper().getContactCount();
        assertEquals(after.size(), before.size() + 1);

        //contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        //contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        //before.add(contact);
        //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
        //Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(ById);
        //after.sort(ById);
        //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(contact.
                withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
