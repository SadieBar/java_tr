package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0)//(!app.group().isGroupPresent()) {
      app.group().create(new GroupData().withGroupName("test1"));
      app.goTo().groupPage();
    }



  @Test
  public void groupModification() {
    //int before = app.getContactHelper().getContactCount();
    Set<GroupData> before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    //int index = before.size()-1;
    GroupData newdata = new GroupData()
            .withId(modifiedGroup.getId())
            .withGroupName("test2").withHeader("test3").withFooter("test3");

    app.group().modify(newdata);
    app.goTo().groupPage();
    //int after = app.getContactHelper().getContactCount();
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(newdata);
    //Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(ById);
    //after.sort(ById);
    Assert.assertEquals(before, after);
  }


}
