package ru.stqa.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        //int index = before.size()-1;
        app.group().delete(deletedGroup);
        app.goTo().groupPage();
        //int after = app.group().getGroupCount();
        Groups after = app.group().all();
        assertEquals(after.size(), before.size()-1);
        //before.remove(index);
        //before.remove(deletedGroup);
        assertThat(after, equalTo(before.without(deletedGroup)));
    }


}
