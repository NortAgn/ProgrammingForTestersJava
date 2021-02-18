package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "Agnia", "Khay", "89276839311",
              "agniianort@gmail.com", "test2")
      );
    }
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteChosenContact();
    app.getContactHelper().submitContactDeletion();
  }
}
