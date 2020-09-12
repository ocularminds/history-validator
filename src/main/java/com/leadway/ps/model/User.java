package com.leadway.ps.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dev.io
 */
@Entity
@Table(name = "th_users")
public class User {
  @Id
  @Column(name = "userid", nullable = false, length = 22)
  private String id;

  @Column(name = "surname", nullable = false, length = 26)
  private String surname;

  @Column(name = "firstname", nullable = false, length = 26)
  private String name;

  @Column(name = "role", nullable = false, length = 26)
  private String role;

  @Column(name = "dept", nullable = true, length = 6)
  private String department;

  public User() {}

  public User(
    String id,
    String surname,
    String name,
    String role,
    String department
  ) {
    this.id = id;
    this.surname = surname;
    this.name = name;
    this.role = role;
    this.department = department;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSurname() {
    return this.surname;
  }

  public void setSurname(String sname) {
    this.surname = sname;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String dept) {
    this.department = dept;
  }
}
