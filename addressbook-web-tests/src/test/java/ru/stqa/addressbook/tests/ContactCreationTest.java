package ru.stqa.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.Groups;

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
    @Test//(dataProvider = "validContactsFromXml")
    public void contactCreationTest() {
        //app.getContactHelper().gotoAddNewContact();
        //app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanov", "ii", "+79151111111", "iivanov@mail.ru"));
        //app.getContactHelper().submitContact();
        //int before = app.getContactHelper().getContactCount();
  /*    app.goTo().groupPage();
      //int before = app.group().count();
      Groups before = app.db().groups();//app.group().all();
      app.group().create(group);
      app.goTo().groupPage();
      assertThat(app.group().count(),equalTo(before.size()+1));
*/
      //Groups after = app.db().groups();

      Contacts before = app.db().contacts();

      /*  Contacts before = app.db().contacts();//app.getContactHelper().all();
        //File photofile = new File("addressbook-web-tests/src/test/resources/stru.png");*/
        ContactData contact = new ContactData()
                .withName("Ivan").withSurname("Ivanov").withNick("ii")
                .withMobilePhone("+79151111111").withWorkPhone("2221111")
                .withEmail2("boss@inkognito").withEmail("Test.ru")
                .withEmail3("secretaty@inkognito")
                .withHomePhone("1234567").withAddress("L street, b.15");
        app.getContactHelper().createContact(contact);//here

        Contacts after = app.db().contacts();//app.getContactHelper().all();

        //int after = app.getContactHelper().getContactCount();
      //assertEquals(Integer.valueOf(after.size()), Integer.toString(before.size() + 1));

      //contact.withId(after.stream().max((Comparator<ContactData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        //contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        //before.add(contact);
        //Assert.assertEquals(new HashSet<>(before),new HashSet<>(after));
        //Comparator<? super ContactData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(ById);
        //after.sort(ById);
        //Assert.assertEquals(before, after);
        Assert.assertEquals(after.size(), before.size()+1);
        assertThat(after, equalTo(before.withAdded(contact.
                withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        app.goTo().homePage();

      assertThat(after, equalTo(before.withAdded(contact.
              withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

        //app.getContactHelper().verifyContact(contact);
        //app.getContactHelper().clickUpdate();
      //int after = app.getContactHelper().getContactCount();
      //Contacts after1 = app.db().contacts();// app.getContactHelper().all();


    }


}
