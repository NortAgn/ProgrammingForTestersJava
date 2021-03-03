package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewContactCreationTests extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Agnia").withLastname("Khay")
            .withMobilenumber("89276839314").withEmail("agniianort@gmail.com").withGroup("test2");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
