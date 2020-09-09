package com.leadway.ps.model;

/**
 *
 * @author Dev.io
 */
public class Department {
  //001, name, role
  private String code;
  private String name;

  public Department(){}

  public Department(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
