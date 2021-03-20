package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    File photo = new File("src/test/resources/girl.png");
    if (app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Agnia").withLastname("Khay").
              withMobilePhone("222").withHomePhone("999").withWorkPhone("852").withEmail("agniianort@gmail.com").
              withPhoto(photo).withFax("89-9632"));
              //.withGroup("test2"));
    }
  }

  @Test

  public void testContactDeletion() {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.goTo().contactPage();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
