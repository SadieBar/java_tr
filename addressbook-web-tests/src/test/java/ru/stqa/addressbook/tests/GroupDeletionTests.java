package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test1","test2","test3"));
            app.goTo().groupPage();
        }
    }
    @Test
    public void testGroupDeletion() {
        //int before = app.group().getGroupCount();
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        delete(index);
        app.goTo().groupPage();
        //int after = app.group().getGroupCount();
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), index);
        before.remove(index);
        Assert.assertEquals(before,after);
    }


}
