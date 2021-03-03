package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
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

  public void initContactModificationByID(int id) {
    wd.findElement (By.cssSelector("a[href='edit.php?id=" + id + "']")).click(); }

  public void updateContactForm() {
    click(By.name("update"));
  }

  public void chooseContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void chooseContactById(int id) {
    wd.findElement (By.cssSelector("input[value='" + id + "']")).click();

  }

  public void deleteChosenContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(ContactData contactData) {
    initContactModificationByID(contactData.getId());
    fillContactForm(contactData, false);
    updateContactForm();
    returnToHomePage();
  }


  public void delete(int index) {
    chooseContact(index);
    deleteChosenContact();
    submitContactDeletion();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    chooseContactById(contact.getId());
    deleteChosenContact();
    submitContactDeletion();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//img[@alt='Edit']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    //return wd.findElements(By.xpath("//img[@alt='Edit']")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }


}


