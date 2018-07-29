package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {
  @Test
  public void groupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isGroupPresent()) {
      app.getGroupHelper().createGroup(new GroupData("test1","test2","test3"));
      app.getNavigationHelper().gotoGroupPage();
    }
    //int before = app.getContactHelper().getContactCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-1;
    app.getGroupHelper().selectGroup(index);
    app.getGroupHelper().initGroupModification();
    GroupData newdata = new GroupData("test2","test3","test3", before.get(index).getId());
    app.getGroupHelper().fillGroupForm(newdata);
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    //int after = app.getContactHelper().getContactCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(newdata);
    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }
}
