package ru.stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  @Parameter(names="-c", description="Contact count")
  public int count;
  @Parameter(names="-f", description="Target file")
  public String file;
  @Parameter(names="-d", description="Data format")
  public String format;
  public static void main(String []args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander commander = new JCommander(generator);
    try {
      commander.parse(args);
    }catch (ParameterException e) {
      commander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format "+format);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try(Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }


  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i=0;i<count;i++) {
      contacts.add(new ContactData().withName(String.format("Ivan %s",i))
              .withSurname(String.format("Ivanov %s",i))
              .withHomePhone(String.format("495 %s" ,i))
              .withMobilePhone(String.format("915 %s" ,i))
              .withWorkPhone(String.format("915 %s" ,i))
              .withEmail(String.format("%s@mail.ru" ,i))
              .withNick(String.format("nick%s" ,i))
              //.withPhoto(new File("addressbook-web-tests/src/test/resources/stru.png"))
              .withAddress(String.format("City %s" ,i))
      );
    }
    return contacts;
  }
}
