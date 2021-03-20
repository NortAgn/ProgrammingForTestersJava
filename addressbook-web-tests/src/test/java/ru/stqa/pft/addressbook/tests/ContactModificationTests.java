package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    File photo = new File("src/test/resources/girl.png");
    if (app.db().contacts().size() == 0){
      app.goTo().contactPage();
      app.contact().create(new ContactData().withFirstname("Agnia").withLastname("Khay").
              withMobilePhone("222").withHomePhone("999").withWorkPhone("852").withEmail("agniianort@gmail.com").
              withPhoto(photo).withFax("785-9630"));
    }
  }

  @Test
  public void testContactModification() {
    File photo = new File("src/test/resources/girl.png");
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactData = new ContactData().withId(modifiedContact.getId()).withFirstname("Agnes")
            .withLastname("Khayrutdinova").withMobilePhone("225").withHomePhone("879")
            .withWorkPhone("125").withPhoto(photo).withEmail("agniianorts@gmail.com").withFax("785-200252");
    app.goTo().contactPage();
    app.contact().modify(contactData);
    Contacts after = app.db().contacts();
    assertEquals(after.size(),before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));

  }


}
