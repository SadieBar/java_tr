package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactComparisonTests extends TestBase {


  public static void mainFlow (String args[]) {
    testComparison();
 }
  @Test
  public void testComparison() {
    app.goTo().homePage();

    if ("".equals(properties.getProperty("selenium.server")))
      if (!app.getContactHelper().isContactPresent()) {
      app.getContactHelper().createContact(new ContactData().withName("Ivan")
              .withSurname("Ivanov").withNick("ii").withMobilePhone("+79151111111")
              .withEmail("iivanov@mail.ru").withEmail2("iivanov2@mail.ru").withEmail3("iivanov2@mail.ru")
              .withAddress("abc123").withWorkPhone("111").withHomePhone("222")
              .withEmail2("iviv@mail.ru").withEmail3("ii@mail.ru"));
    }
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    ContactData contactComplited = app.getContactHelper().all().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contactComplited);
    //сверкаwith
    assertThat(contactComplited.getEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contactComplited.getAllphones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contactComplited.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactComparisonTests::cleaned)
            .collect(Collectors.joining("\n"));
  }
  private String mergeEmails(ContactData contact){
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]]","");
  }
}
