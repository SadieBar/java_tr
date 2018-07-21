package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {
    
    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().gotoGroupPage();
        if (!app.getGroupHelper().isGroupPresent()) {
            app.getGroupHelper().createGroup(new GroupData("test1","test2","test3"));
            app.getNavigationHelper().gotoGroupPage();
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getNavigationHelper().gotoGroupPage();
    }

}
