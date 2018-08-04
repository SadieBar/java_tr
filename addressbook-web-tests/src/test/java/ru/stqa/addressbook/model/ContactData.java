package ru.stqa.addressbook.model;

import java.util.Objects;

public class ContactData {
  private String name;
  private String surname;
  private String nick;
  private String phone;
  private String email;
  private int id;

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
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", id=" + id +
            '}';
  }

  /*public ContactData(String name, String surname, String nick, String phone, String email, int id) {
    this.name = name;
    this.surname = surname;
    this.nick = nick;
    this.phone = phone;
    this.email = email;
    this.id = id;
  }

  public ContactData(String name, String surname, String nick, String phone, String email) {
    this.name = name;
    this.surname = surname;
    this.nick = nick;
    this.phone = phone;
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

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public int getId() { return id;}

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

  public ContactData withPhone(String phone) {
    this.phone = phone;
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
}
