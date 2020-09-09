package com.leadway.ps.model;

public class Resource {
  String group;
  String link;
  String label;
  String priviledge;

  public Resource(String group, String link, String label, String role) {
    this.group = group;
    this.link = link;
    this.label = label;
    this.priviledge = role;
  }

  public String getGroup() {
    return this.group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getLink() {
    return this.link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getPriviledge() {
    return this.priviledge;
  }

  public void setPriviledge(String priviledge) {
    this.priviledge = priviledge;
  }
}
