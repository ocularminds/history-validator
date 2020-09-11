package com.leadway.ps.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
public class Resource {
  @Id
  @Column(name = "rid", nullable = false, length = 22)
  private String id;

  @Column(name = "grp", nullable = false, length = 22)
  String group;

  @Column(name = "link", nullable = false, length = 26)
  String link;

  @Column(name = "label", nullable = false, length = 26)
  String label;

  @Column(name = "priviledge", nullable = false, length = 15)
  String priviledge;

  public Resource(){}
  public Resource(String group, String link, String label, String role) {
    this.group = group;
    this.link = link;
    this.label = label;
    this.priviledge = role;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
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
