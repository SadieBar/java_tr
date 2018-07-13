package ru.stqa.addressbook.model;

public class ContactData {
  private final String name;
  private final String surname;
  private final String nick;
  private final String phone;
  private final String email;

  public ContactData(String name, String surname, String nick, String phone, String email) {
    this.name = name;
    this.surname = surname;
    this.nick = nick;
    this.phone = phone;
    this.email = email;
  }

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
}
