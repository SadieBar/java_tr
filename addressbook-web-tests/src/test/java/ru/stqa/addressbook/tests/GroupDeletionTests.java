package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withGroupName("test1"));
            app.goTo().groupPage();
        }
    }
    @Test
    public void testGroupDeletion() {
        //int before = app.group().getGroupCount();
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        //int index = before.size()-1;
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        //int after = app.group().getGroupCount();
        Set<GroupData> after = app.group().all();
        //Assert.assertEquals(after.size(), index);
        //before.remove(index);
        before.remove(deletedGroup);
        Assert.assertEquals(before,after);
    }


}
