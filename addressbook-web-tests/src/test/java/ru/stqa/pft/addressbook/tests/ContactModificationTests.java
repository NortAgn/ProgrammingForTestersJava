package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Agnia", "Khayrutdinova", "89276839312", "agniianort@gmail.com"));
    app.getContactHelper().updateContactForm();
    app.getContactHelper().returnToHomePage();
  }
}
