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
@Table(name = "th_departments")
public class Department implements java.io.Serializable{
  
  @Id
  @Column(name = "code", nullable = false, length = 22)
  private String code;

  @Column(name = "name", nullable = false, length = 35)
  private String name;

  public Department() {}

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
