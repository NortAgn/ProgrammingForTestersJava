package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

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
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    //type(By.name("email2"), contactData.getEmail2());
    //type(By.name("email3"), contactData.getEmail3());
    //type(By.name("address"), contactData.getAddress());
    attach(By.name("photo"), contactData.getPhoto());


    if (creation){
      if (contactData.getGroups().size() > 0){
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().
                next().getName());
      }
    }else{
    Assert.assertFalse((isElementPresent(By.name("new_group"))));
    }
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

//  public void initContactModificationByID(int id) {
//    wd.findElement (By.cssSelector("a[href='edit.php?id=" + id + "']")).click(); }

  public void updateContactForm() {
    click(By.name("update"));
  }

  public void chooseContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void chooseContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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
      String allAddresses = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones)
              .withAllAddresses(allAddresses)
              .withAllEmails(allEmails));
    }
    return contacts;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationByID(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationByID(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("input[value='%s']/../../td[8]/a",id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();

  }

  public void chooseGroupFilter(String groupName) {
    WebElement group = wd.findElement(By.name("group"));
    new Select(group).selectByVisibleText(groupName);
  }

  public void addToGroup(String groupName) {
    WebElement toGroup = wd.findElement(By.name("to_group"));
    new Select(toGroup).selectByVisibleText(groupName);
    wd.findElement(By.name("add")).click();
  }

  public void removeFromGroup(String groupName) {
    wd.findElement(By.name("remove")).click();
  }
}



