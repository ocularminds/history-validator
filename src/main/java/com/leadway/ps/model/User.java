package com.leadway.ps.model;

/**
 *
 * @author Dev.io
 */
public class User {
  private String id;
  private String surname;
  private String name;
  private String role;
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
