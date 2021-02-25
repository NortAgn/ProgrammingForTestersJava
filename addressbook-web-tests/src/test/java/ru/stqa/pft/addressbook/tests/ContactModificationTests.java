package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

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
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().chooseContact(before.size()- 1);
    app.getContactHelper().initContactModification();

    ContactData contactData = new ContactData(before.get(before.size()- 1) .getId(),
            "Agnia", "Khay", "89276839391",
            "agniianort@gmail.com", null
    );
    app.getContactHelper().fillContactForm(contactData,false);
    app.getContactHelper().updateContactForm();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()- 1);
    before.add (contactData);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }
}
