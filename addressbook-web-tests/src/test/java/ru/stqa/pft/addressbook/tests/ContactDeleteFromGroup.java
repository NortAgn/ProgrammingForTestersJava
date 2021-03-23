package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

public class ContactDeleteFromGroup extends TestBase{
  public final String groupName = "group" + ThreadLocalRandom.current().nextLong();
  private final String contactName = "Alisa" + ThreadLocalRandom.current().nextLong();

  @BeforeMethod
  public void ensurePreconditions() {
    File photo = new File("src/test/resources/girl.png");
    app.goTo().contactPage();
    app.contact().create(new ContactData().withFirstname(contactName).withLastname("Frant")
            .withMobilePhone("563").withHomePhone("999").withWorkPhone("852").withEmail("agniianort@gmail.com")
            .withPhoto(photo).withFax("785-9630"));
    app.goTo().groupPage();
    GroupData group = new GroupData()
            .withName(groupName)
            .withHeader("header")
            .withFooter("footer");
    app.group().create(group);
    app.contact().returnToHomePage();
    app.contact().chooseGroupFilter("[none]");
    app.contact().chooseContactById(app.db().contacts().findByName(contactName).getId());
    app.contact().addToGroup(groupName);
  }

  @Test
  public void testContactDeletionFromGroup(){
    app.contact().returnToHomePage();
    app.contact().chooseGroupFilter(groupName);
    app.contact().chooseContactById(app.db().contacts().findByName(contactName).getId());
    app.contact().removeFromGroup(groupName);
    ContactData contactData = app.db().contacts().findByName(contactName);
    Assert.assertEquals(contactData.getGroups().size(), 0);
  }
}

