package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    initGroupCreation();
    fillGroupForm(new GroupData("test2", "test4", "test5"));
    submitGroupCreation();
    returnToGroupPage();
    //wd.findElement(By.linkText("Logout")).click();
  }

}
