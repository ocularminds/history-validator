package com.leadway.ps.api;

import com.leadway.ps.InvalidAccessError;
import com.leadway.ps.model.Department;
import com.leadway.ps.model.User;
import com.leadway.ps.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = { "username", "greetings", "fullname" })
public class Users {
  UserService users;

  @Autowired
  public Users(UserService users) {
    this.users = users;
  }

  @ExceptionHandler(InvalidAccessError.class)
  public String handleAnyException(Throwable ex, HttpServletRequest request) {
    return "denied";
  }

  @RequestMapping("/users")
  public String getAll(ModelMap model) {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    List<User> requests = users.findAll();
    List<Department> departments = users.findAllDepartments();
    model.put("departments", departments);
    model.put("users", requests);
    model.put("user", new User());
    model.put("roles", users.getRoles());
    return "users";
  }

  @RequestMapping("/users/{id}")
  public String getUser(@PathVariable(value = "id") String id, ModelMap model)
    throws Exception {
    String name = (String) model.get("username");
    if (name == null) return "redirect:/login";
    User r = users.get(id);
    List<User> requests = users.findAll();
    List<Department> departments = users.findAllDepartments();
    model.put("departments", departments);
    model.put("users", requests);
    model.put("user", r);
    model.put("roles", users.getRoles());
    return "users";
  }

  @PostMapping("/users")
  public String save(
    ModelMap model,
    @ModelAttribute(value = "user") User user
  ) {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    users.add(user);
    return "redirect:/users";
  }

  @RequestMapping("/departments")
  public String getAllDepartments(ModelMap model) {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    List<Department> requests = users.findAllDepartments();
    model.put("departments", requests);
    model.put("department", new Department());
    return "departments";
  }

  @RequestMapping("/departments/{id}")
  public String getDepartment(
    @PathVariable(value = "id") String id,
    ModelMap model
  )
    throws Exception {
    String name = (String) model.get("username");
    if (name == null) return "redirect:/login";
    List<Department> requests = users.findAllDepartments();
    model.put("departments", requests);
    Department r = users.getDepartment(name);
    model.put("department", r);
    return "department";
  }

  @RequestMapping("/department")
  public String newDepartment(ModelMap model) throws Exception {
    String name = (String) model.get("username");
    if (name == null) return "redirect:/login";
    model.put("department", new Department());
    return "department";
  }

  @PostMapping("/departments")
  public String save(
    ModelMap model,
    @ModelAttribute(value = "user") Department department
  ) {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    return "redirect:/login";
  }
}
