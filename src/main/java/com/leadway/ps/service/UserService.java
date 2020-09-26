package com.leadway.ps.service;

import com.leadway.ps.InvalidAccessError;
import com.leadway.ps.model.Department;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
import com.leadway.ps.repository.DepartmentRepository;
import com.leadway.ps.repository.ResourceRepository;
import com.leadway.ps.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev.io
 */
@Service
public class UserService {
  final String[] roles;
  final UserRepository repository;
  final ResourceRepository resources;
  final DepartmentRepository departments;

  @Autowired
  public UserService(
    UserRepository userRepository,
    ResourceRepository resourceRepository,
    DepartmentRepository departmentRepository
  ) {
    this.departments = departmentRepository;
    repository = userRepository;
    roles = new String[] { "INITIATOR", "REVIEWER", "AUTHORIZER" };
    this.resources = resourceRepository;
  }

  public String[] getRoles() {
    return roles;
  }

  public List<User> findAll() {
    return repository.findAll();
  }

  public User get(String id) throws Exception {
    return repository
      .findById(id)
      .orElseThrow(() -> new Exception("User not found"));
  }

  public User put(String username, User user) throws Exception {
    User currentUser = get(username);
    currentUser.setName(user.getName());
    currentUser.setSurname(user.getSurname());
    currentUser.setRole(user.getRole());
    currentUser.setDepartment(user.getDepartment());
    return repository.save(user);
  }

  public void add(User user) {
    String s1 = Integer.toString((int) ((Math.random() * 100) + 100));
    String s2 = Long.toString(System.currentTimeMillis());
    if(user.getId() == nul || user.getId().trim().isEmpty()){
       user.setId(s1 + new StringBuilder(s2).reverse().substring(0, 6));
    }
    repository.save(user);
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

  public void add(Class typ, String[][] data) {
    if (typ == Department.class) {
      List<Department> records = new ArrayList<>();
      for (String[] d : data) {
        System.out.println("--- \t" + Arrays.toString(d));
        records.add(new Department(d[0], d[1]));
      }
      departments.saveAll(records);
      departments.flush();
    } else if (typ == Resource.class) {
      List<Resource> records = new ArrayList<>();
      Resource resource;
      for (String[] r : data) {
        System.out.println("--- \t" + Arrays.toString(r));
        resource = new Resource(r[0], r[1], r[2], r[3]);
        resource.setId(nextId());
        records.add(resource);
      }
      resources.saveAll(records);
      resources.flush();
    } else {
      List<User> records = new ArrayList<>();
      User user;
      for (String[] u : data) {
        System.out.println("--- \t" + Arrays.toString(u));
        user = new User(u[0], u[1], u[2], u[3], u[4]);
        records.add(user);
      }
      repository.saveAll(records);
      repository.flush();
    }
  }

  public void add(Department department) {
    departments.save(department);
  }

  public void add(Resource resource) {
    String s1 = Integer.toString((int) ((Math.random() * 100) + 100));
    String s2 = Long.toString(System.currentTimeMillis());
    resource.setId(s1 + new StringBuilder(s2).reverse().substring(0, 5));
    resources.save(resource);
  }

  public Optional<Resource> getResource(String link) {
    return resources.findByLink(link);
  }

  public boolean hasRole(String userid, String page)
    throws InvalidAccessError, Exception {
    if (userid == null) {
      return false;
    }
    User user = get(userid);
    Resource resource = getResource(page)
      .orElseThrow(() -> new InvalidAccessError("Unknown resource"));
    return (
      resource != null && (resource.getPriviledge().equals(user.getRole()))
    );
  }

  private String nextId() {
    String s1 = Integer.toString((int) ((Math.random() * 100) + 100));
    String s2 = Long.toString(System.currentTimeMillis());
    return s1 + new StringBuilder(s2).reverse().substring(0, 5);
  }
}
