package ru.stqa.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
    
    @Test
    public void testGroupDeletion() {

        gotoGroupPage();
        //wd.findElement(By.linkText("groups")).click();
        selectGroup();
        deleteSelectedGroups();
        gotoGroupPage();
    }

}
