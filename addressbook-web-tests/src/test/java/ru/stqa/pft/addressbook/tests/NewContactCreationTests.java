package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class NewContactCreationTests extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstname("Agnia").withLastname("Khay")
            .withMobilenumber("89276839314").withEmail("agniianort@gmail.com").withGroup("test2");
    app.contact().create(contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId()) ;
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
