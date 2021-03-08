package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Agnia").withLastname("Khay").
              withMobilePhone("222").withHomePhone("111").withWorkPhone("333").
              withAddress("78962 Honolulu,896").withEmail("agniianort@gmail.com").
              withEmail2("nortagnia@gmail.com").withEmail3("anort@yahoo.com"));
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().contactPage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAllAddresses(), equalTo(mergeAddresses(contactInfoFromEditForm)));

  }

  private <T> String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  private <T> String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleanedEmails)
            .collect(Collectors.joining("\n"));
  }

  private <T> String mergeAddresses(ContactData contact) {
    return Arrays.asList(contact.getAddress())
            .stream()
            .filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleanedAddresses)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "")
            .replaceAll("[-()]", "");
  }

  public static String cleanedEmails(String email) {
    return email.replaceAll("\\s", "")
            .replaceAll("[()]", "");
  }

  public static String cleanedAddresses(String address) {
    return address.replaceAll("[()]", "");
  }

}
