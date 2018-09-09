package ru.stqa.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
  @Column(name="email2")
  @Type(type="text")
  private String email2;
  @Column(name="email3")
  @Type(type="text")
  private String email3;
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
  private String emails;
  @Transient
  private String allphones;

  @Column(name="address")
  @Type(type="text")
  private String address;
  //@Column(name="photo")
  //@Type(type="text")
  //@Transient
  //private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  public Groups getGroups() {
    return new Groups(groups);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(nick, that.nick) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, nick, mobilePhone, email, homePhone, workPhone, id, address);
  }

  @Transient
  //public File getPhoto() {
  //  return new File(photo);
  //}

  //public ContactData withPhoto(File photo) {
   // this.photo = photo.getPath();
   // return this;
  //}

  @Override
  public String toString() {
    return "ContactData{" +
            "name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", nick='" + nick + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2+ '\'' +
            ", email3='" + email3 + '\'' +
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



  public String getEmails() {
    return emails;
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

  public String getEmail2() {
    return email2;
  }

    public String getEmail3() {
      return email3;
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
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String emails) {
    this.emails = emails;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public String getAllphones(){
  return allphones;
  }

  public ContactData withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }

}
