package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;
@Entity
@XStreamAlias("contact")
@Table (name = "addressbook")
public class ContactData {

  @XStreamOmitField
  @Id
  @Column (name = "id")
  private  int id = Integer.MAX_VALUE;
  //private int id;

  @Expose
  @Column (name = "firstname")
  private String firstname;

  @Expose
  @Column (name = "lastname")
  private String lastname;

  @Transient
  private String group;

  @Column (name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column (name = "home")
  @Type(type = "text")
  private String homePhone;

  @Column (name = "work")
  @Type(type = "text")
  private String workPhone;

  @Column (name = "fax")
  @Type(type = "text")
  private String fax;

  @Transient
  private String allPhones;

  @Expose
  @Column (name = "email")
  @Type(type = "text")
  private String email;
  @Transient
  private String email2;
  @Transient
  private String email3;

  @Transient
  private String allEmails;
  @Transient
  private String address;

  @Transient
  private String allAddresses;

  @Column (name = "photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    return  new File (photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getFax() { return fax; }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public String getEmail2() {
    return email2;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllAddresses() {
    return allAddresses;
  }

  public ContactData withAllAddresses(String allAddresses) {
    this.allAddresses = allAddresses;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public String getGroup() {
    return group;
  }

//  @Override
//  public String toString() {
//    return "ContactData{" +
//            "id='" + id + '\'' +
//            ", firstname='" + firstname + '\'' +
//            ", lastname='" + lastname + '\'' +
//            '}';
//  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }


  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, mobilePhone, homePhone, workPhone, email);
  }
}
