package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String mobilenumber;
  private final String email;
  private String group;

  public ContactData(String firstname, String lastname, String mobilenumber, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobilenumber = mobilenumber;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobilenumber() {
    return mobilenumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
