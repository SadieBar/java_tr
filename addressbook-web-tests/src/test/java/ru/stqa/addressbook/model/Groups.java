package ru.stqa.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {
  private Set<GroupData> delegate;

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  public Groups(Groups groupData) {
    this.delegate = new HashSet<GroupData>(groupData.delegate);
  }

  public Groups(Collection<GroupData> groups) {
    this.delegate = new HashSet<GroupData>(groups);
  }

  @Override
  public Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded(GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups without(GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
}
