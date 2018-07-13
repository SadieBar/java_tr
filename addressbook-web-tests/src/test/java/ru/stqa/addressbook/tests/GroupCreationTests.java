package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        //wd.findElement(By.xpath("//div[@id='footer']//li[.='php-addressbook v8.2.5']")).click();
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        app.getGroupHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.getGroupHelper().submitGroupCreation();
        app.getNavigationHelper().gotoGroupPage();
    }

}
