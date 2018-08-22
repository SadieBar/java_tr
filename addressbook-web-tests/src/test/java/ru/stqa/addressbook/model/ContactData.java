package ru.stqa.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@javax.persistence.Table(name="addressbook")
public class ContactData {
  @Column(name="firstname")
  private String name;
  @Column(name="lastname")
  private String surname;
  @Column(name="nickname")
  private String nick;
  @Column(name="mobile")
  @Type(type="text")
  private String mobilePhone;
  @Column(name="email")
  @Type(type="text")
  private String email;
  @Column(name="home")
  @Type(type="text")
  private String homePhone;
  @Column(name="work")
  @Type(type="text")
  private String workPhone;
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id;
  @Transient
  private String group;
  @Transient
  private String allphones;
  @Column(name="address")
  @Type(type="text")
  private String address;
  @Column(name="photo")
  @Type(type="text")
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, id);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", nick='" + nick + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", email='" + email + '\'' +
            ", id=" + id +
            '}';
  }

  /*public ContactData(String name, String surname, String nick, String mobilePhone, String email, int id) {
    this.name = name;
    this.surname = surname;
    this.nick = nick;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.id = id;
  }

  public ContactData(String name, String surname, String nick, String mobilePhone, String email) {
    this.name = name;
    this.surname = surname;
    this.nick = nick;
    this.mobilePhone = mobilePhone;
    this.email = email;
    this.id = Integer.MAX_VALUE;
  }
*/

  public String getName() {
    return name;
  }

  public String getSurname() {
    return surname;
  }

  public String getNick() {
    return nick;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getEmail() {
    return email;
  }

  public int getId() { return id;}

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withSurname(String surname) {
    this.surname = surname;
    return this;
  }

  public ContactData withNick(String nick) {
    this.nick = nick;
    return this;
  }

  public ContactData withMobilePhone(String phone) {
    this.mobilePhone = phone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public String getAllphones() {
    return allphones;
  }

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }
}
