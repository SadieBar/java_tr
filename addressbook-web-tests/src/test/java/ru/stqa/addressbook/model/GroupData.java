package ru.stqa.addressbook.model;

import java.util.Objects;

public class GroupData {
  private String groupName;
  private String header;
  private String footer;
  private int id = Integer.MAX_VALUE;

  public GroupData withGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(groupName, groupData.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName, id);
  }



  public int getId() {
    return id;
  }


/*  public GroupData(String groupName, String header, String footer) {
    this.groupName = groupName;
    this.header = header;
    this.footer = footer;
    this.id = Integer.MAX_VALUE;
  }

  public GroupData(String groupName, String header, String footer, int id) {
    this.groupName = groupName;
    this.header = header;
    this.footer = footer;
    this.id = id;
  }
*/
  public String getGroupName() {
    return groupName;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }


  @Override
  public String toString() {
    return "GroupData{" +
            "groupName='" + groupName + '\'' +
            ", id=" + id +
            '}';
  }
}
