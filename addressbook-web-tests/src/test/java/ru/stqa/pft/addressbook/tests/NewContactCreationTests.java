package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class NewContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))){
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());  //List<ContactData>.class
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

//(dataProvider = "validContactsFromJson")
//  @Test
//  public void testNewContactCreation() throws Exception {
//   File photo = new File("src/test/resources/girl.png");
//   Contacts before = app.db().contacts();
//   ContactData contact = new ContactData().withFirstname("Agnia").withLastname("Khay")
//            .withMobilePhone("89274916958").withWorkPhone("896").withHomePhone("0500")
//            .withEmail("agnesnort@gmail.com").withPhoto(photo).withFax("9632-98");
//    app.contact().create(contact);
//    Contacts after = app.db().contacts();
//    assertThat(after.size(), equalTo(before.size() + 1));
//    assertThat(after, equalTo(before.withAdded(
//            contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
//  }

//  @Test
//  public void testCurrentDir(){
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File ("src/test/resources/girl.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

  @Test
  public void testNewContactCreation() throws Exception {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/girl.png");
    ContactData newContact = new ContactData().withFirstname("Anna").withLastname("Khan")
            .withMobilePhone("89274916958").withWorkPhone("896").withHomePhone("0500")
            .withEmail("agnesnort@gmail.com").withPhoto(photo).withFax("9632-98")
            .inGroup(groups.iterator().next());
    app.contact().goToAddNewContact();
    app.contact().fillContactForm(newContact, true);
    app.contact().submitContactCreation();
    app.contact().returnToHomePage();
    Contacts after = app.db().contacts();
  }
}
