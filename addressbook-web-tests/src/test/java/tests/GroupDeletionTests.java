package tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {
    
    @Test
    public void testGroupDeletion() {

        app.gotoGroupPage();
        //wd.findElement(By.linkText("groups")).click();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.gotoGroupPage();
    }

}
