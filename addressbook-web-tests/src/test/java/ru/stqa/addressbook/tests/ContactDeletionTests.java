package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{
  @Test
  public void contactDeletion(){
    app.getContactHelper().clickSelected();
    app.getContactHelper().clickDelete();
  }
}
