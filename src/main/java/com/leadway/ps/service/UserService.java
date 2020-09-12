package com.leadway.ps.service;

import com.leadway.ps.InvalidAccessError;
import com.leadway.ps.model.Department;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
import com.leadway.ps.repository.DepartmentRepository;
import com.leadway.ps.repository.ResourceRepository;
import com.leadway.ps.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev.io
 */
@Service
public class UserService {
  final String[] roles;
  final UserRepository users;
  final ResourceRepository resources;
  final DepartmentRepository departments;

  @Autowired
  public UserService(
    UserRepository userRepository,
    ResourceRepository resourceRepository,
    DepartmentRepository departmentRepository
  ) {
    this.departments = departmentRepository;
    this.users = userRepository;
    roles = new String[] { "INITIATOR", "REVIEWER", "AUTHORIZER" };
    this.resources = resourceRepository;
  }

  public List<User> findAll() {
    return users.findAll();
  }

  public User get(String id) throws Exception {
    return users
      .findById(id)
      .orElseThrow(() -> new Exception("User not found"));
  }

  public User put(String username, User user) throws Exception {
    User currentUser = get(username);
    currentUser.setName(user.getName());
    currentUser.setSurname(user.getSurname());
    currentUser.setRole(user.getRole());
    currentUser.setDepartment(user.getDepartment());
    return users.save(user);
  }

  public void add(User user) {
    String s1 = Integer.toString(((int)Math.rand()*100)+100);
    String s2 = Long.toString(System.currentTimeMillis);
    user.setId(s1+new StringBuilder(s2).reverse().substring(0,6));
    users.save(user);
  }

  public List<Department> findAllDepartments() {
    return departments.findAll();
  }

  public Department getDepartment(String id) {
    return departments.getOne(id);
  }

  public Department put(String deptno, Department department) {
    Department current = departments.getOne(deptno);
    current.setName(department.getName());
    return departments.save(department);
  }

  public void add(Department department) {
    departments.save(department);
  }

  public void add(Resource resource) {
    String s1 = Integer.toString(((int)Math.rand()*100)+100);
    String s2 = Long.toString(System.currentTimeMillis);
    resource.setId(s1+new StringBuilder(s2).reverse().substring(0,5));
    resources.save(resource);
  }

  public Optional<Resource> getResource(String link) {
    return resources.findById(link);
  }

  public boolean hasRole(String userid, String page)
    throws InvalidAccessError, Exception {
    User user = get(userid);
    if (userid == null) return false;
    Resource resource = getResource(page)
      .orElseThrow(() -> new InvalidAccessError("Unknown resource"));
    return (
      resource != null && (resource.getPriviledge().equals(user.getRole()))
    );
  }
}
