package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class NewContactCreationTests extends TestBase {

  @Test
  public void testNewContactCreation() throws Exception {
    app.getContactHelper().goToAddNewContact();
    app.getContactHelper().fillContactForm(new ContactData("Agnia", "Khay", "89276839311", "agniianort@gmail.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    //wd.findElement(By.linkText("Logout")).click();
  }


  //private boolean isElementPresent(By by) {
    //try {
     // wd.findElement(by);
     // return true;
    //} catch (NoSuchElementException e) {
     //return false;
    //}
  //}



}
