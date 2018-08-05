package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.addressbook.model.GroupData;

import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        //wd.findElement(By.xpath("//div[@id='footer']//li[.='php-addressbook v8.2.5']")).click();
        app.goTo().groupPage();
        //int before = app.group().count();
        Groups before = app.group().all();
        GroupData group = new GroupData().withGroupName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        app.goTo().groupPage();
        assertThat(app.group().count(),equalTo(before.size()+1));

        Groups after = app.group().all();
        //int after = app.group().count();
        //Assert.assertEquals(after.size(), before.size() + 1);
        //assertThat(after.size(),equalTo(before.size()+1));

        /*int max1 = 0;
        for (GroupData g : after) {
            if (g.getId()>max) max1 = g.getId();
        }*/
        //group.withId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        //before.add(group);

        //Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(ById);
        //after.sort(ById);
        //Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withAdded(group.
                withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withGroupName("test1'").withHeader("test2").withFooter("test3");
        app.group().create(group);
        app.goTo().groupPage();
        assertThat(app.group().count(),equalTo(before.size()));

        Groups after = app.group().all();

        assertThat(after, equalTo(before));
    }

}
