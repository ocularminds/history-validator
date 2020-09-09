package com.leadway.ps.service;

import com.leadway.ps.InvalidAccessError;
import com.leadway.ps.model.Department;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
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
  final Map<String, User> users;
  final Map<String, Department> departments;
  final String[] roles;
  final List<Resource> resources;

  @Autowired
  public UserService() {
    departments = new ConcurrentHashMap<>();
    users = new ConcurrentHashMap<>();
    roles = new String[] { "INITIATOR", "REVIEWER", "AUTHORIZER" };
    resources = new ArrayList<>();
  }

  public List<User> findAll() {
    List<User> records = new ArrayList();
    users.forEach((key, value) -> records.add(value));
    return records;
  }

  public User get(String id) {
    return users.get(id);
  }

  public User put(String username, User user) {
    return users.put(username, user);
  }

  public void add(User user) {
    users.putIfAbsent(user.getId(), user);
  }

  public List<Department> findAllDepartments() {
    List<Department> records = new ArrayList();
    departments.forEach((key, value) -> records.add(value));
    return records;
  }

  public Department getDepartment(String id) {
    return departments.get(id);
  }

  public Department put(String detpno, Department department) {
    return departments.put(detpno, department);
  }

  public void add(Department department) {
    departments.putIfAbsent(department.getCode(), department);
  }

  public void add(Resource resource) {
    resources.add(resource);
  }

  public Optional<Resource> getResource(String link) {
    return resources
      .stream()
      .filter(res -> (res.getLink().contains(link)))
      .findFirst();
  }

  public boolean hasRole(String userid, String page) throws InvalidAccessError {
    User user = get(userid);
    if (userid == null) return false;
    Resource resource = getResource(page)
      .orElseThrow(() -> new InvalidAccessError("Unknown resource"));
    return (
      resource != null && (resource.getPriviledge().equals(user.getRole()))
    );
  }
}
