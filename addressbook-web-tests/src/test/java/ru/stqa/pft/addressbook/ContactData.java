package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String mobilenumber;
  private final String email;

  public ContactData(String firstname, String lastname, String mobilenumber, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobilenumber = mobilenumber;
    this.email = email;
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
}
