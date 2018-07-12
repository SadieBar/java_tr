package tests;

import org.testng.annotations.Test;

import model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        //wd.findElement(By.xpath("//div[@id='footer']//li[.='php-addressbook v8.2.5']")).click();
        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submitGroupCreation();
        app.gotoGroupPage();
    }

}
