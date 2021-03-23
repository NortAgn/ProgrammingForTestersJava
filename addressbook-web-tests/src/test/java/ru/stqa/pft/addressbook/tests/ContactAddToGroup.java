package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class ContactAddToGroup extends TestBase {
  public final String groupName = "group" + ThreadLocalRandom.current().nextLong();
  private final String contactName = "Agnia" + ThreadLocalRandom.current().nextLong();


  @BeforeMethod
  public void ensurePreconditions() {
    File photo = new File("src/test/resources/girl.png");
    app.goTo().contactPage();
    app.contact().create(new ContactData().withFirstname(contactName).withLastname("Khay").
            withMobilePhone("222").withHomePhone("999").withWorkPhone("852").withEmail("agniianort@gmail.com").
            withPhoto(photo).withFax("785-9630"));
    app.goTo().groupPage();
    GroupData group = new GroupData()
            .withName(groupName)
            .withHeader("header")
            .withFooter("footer");
    app.group().create(group);
  }

  @Test
  public void testContactAddToGroup() {
    app.contact().returnToHomePage();
    app.contact().chooseGroupFilter("[none]");
    app.contact().chooseContactById(app.db().contacts().findByName(contactName).getId());
    app.contact().addToGroup(groupName);
    ContactData contactData = app.db().contacts().findByName(contactName);
    Assert.assertEquals(contactData.getGroups().size(), 1);
    Assert.assertEquals(contactData.getGroups().iterator().next().getName(), groupName);
  }
}
