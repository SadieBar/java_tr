package ru.stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Table;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@javax.persistence.Table(name="group_list")
public class GroupData {
  @Expose
  @Column(name="group_name")
  private String groupName;
  @Expose
  @Column(name="group_header")
  @Type(type = "text")
  private String header;
  @Expose
  @Column(name="group_footer")
  @Type(type = "text")
  private String footer;
  @XStreamOmitField
  @Id
  @Column(name="group_id")
  private int id = Integer.MAX_VALUE;

  @ManyToMany(mappedBy = "groups")
  private Set<ContactData> contacts = new HashSet<ContactData>();

  public Contacts getContacts() {
    return new Contacts(contacts);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(groupName, groupData.groupName) &&
            Objects.equals(header, groupData.header) &&
            Objects.equals(footer, groupData.footer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName, header, footer, id);
  }

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
