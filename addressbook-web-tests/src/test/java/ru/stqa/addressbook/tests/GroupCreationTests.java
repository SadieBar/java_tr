package ru.stqa.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        //wd.findElement(By.xpath("//div[@id='footer']//li[.='php-addressbook v8.2.5']")).click();
        app.getNavigationHelper().gotoGroupPage();
        //int before = app.getGroupHelper().getGroupCount();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("test1","test2","test3");
        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        //int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size() + 1);

        /*int max1 = 0;
        for (GroupData g : after) {
            if (g.getId()>max) max1 = g.getId();
        }*/
        group.setId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(group);

        Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }

}
