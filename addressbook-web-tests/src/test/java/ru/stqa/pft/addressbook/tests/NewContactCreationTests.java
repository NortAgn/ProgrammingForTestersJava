package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    app.getContactHelper().createContact(new ContactData("Agnia", "Khay", "89276839311",
            "agniianort@gmail.com", "test2"));
  }
}
