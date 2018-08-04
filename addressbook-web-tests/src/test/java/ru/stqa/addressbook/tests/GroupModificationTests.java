package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0)//(!app.group().isGroupPresent()) {
      app.group().create(new GroupData().withGroupName("test1"));
      app.goTo().groupPage();
    }



  @Test
  public void groupModification() {
    //int before = app.getContactHelper().getContactCount();
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    GroupData newdata = new GroupData()
            .withId(before.get(index).getId())
            .withGroupName("test2").withHeader("test3").withFooter("test3");

    app.group().modify(index, newdata);
    app.goTo().groupPage();
    //int after = app.getContactHelper().getContactCount();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(newdata);
    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }


}
