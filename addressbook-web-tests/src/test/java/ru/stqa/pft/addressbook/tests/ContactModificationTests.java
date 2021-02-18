package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData(
              "Agnia", "Khay", "89276839311",
              "agniianort@gmail.com", "test2")
      );
    }
    app.getContactHelper().initContactModification();
    ContactData contactData = new ContactData(
            "Agnia", "Khay", "89276839391",
            "agniianort@gmail.com", null
    );
    app.getContactHelper().fillContactForm(contactData,false);
    app.getContactHelper().updateContactForm();
    app.getContactHelper().returnToHomePage();
  }
}
