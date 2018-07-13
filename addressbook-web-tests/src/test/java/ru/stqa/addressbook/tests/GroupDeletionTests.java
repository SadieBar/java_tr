package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
    
    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        //wd.findElement(By.linkText("groups")).click();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();
    }

}
