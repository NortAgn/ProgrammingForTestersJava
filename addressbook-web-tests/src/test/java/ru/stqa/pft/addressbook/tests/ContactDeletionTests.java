package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

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
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().chooseContact(before.size() - 1);
    app.getContactHelper().deleteChosenContact();
    app.getContactHelper().submitContactDeletion();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size() - 1);

    before.remove(before.size()-1);
    Assert.assertEquals(before,after);

  }
}
