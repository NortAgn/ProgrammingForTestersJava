package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("mobile"), contactData.getMobilenumber());
    type(By.name("email"), contactData.getEmail());


    //if (creation){
    //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    //}else{
     //Assert.assertFalse((isElementPresent(By.name("new_group"))));
    //}
  }


  public void type(By locator, String text) {
    wd.findElement(locator).click();
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void updateContactForm() {
    click(By.name("update"));
  }

  public void chooseContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void deleteChosenContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    //return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element:elements){
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData( id, firstname, lastname, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}


