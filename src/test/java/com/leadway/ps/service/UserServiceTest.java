package com.leadway.ps.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.leadway.ps.InvalidAccessError;
import com.leadway.ps.RecordRowMapper;
import com.leadway.ps.StatementRowMapper;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Department;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
import com.leadway.ps.repository.DepartmentRepository;
import com.leadway.ps.repository.ResourceRepository;
import com.leadway.ps.repository.UserRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
  @Mock
  JdbcTemplate template;

  @Mock
  DepartmentRepository departments;

  @Mock
  ResourceRepository resources;

  @Mock
  UserRepository repository;

  @InjectMocks
  UserService service;

  final String[] ROLES = new String[] { "INITIATOR", "REVIEWER", "AUTHORIZER" };

  @Test
  public void testGetRoles() {
    assertTrue(service.getRoles().length == 3);
  }

  @Test
  public void testFindAll() {
    List<User> requests = new ArrayList<>();
    requests.add(new User());
    requests.add(new User());
    when(repository.findAll()).thenReturn(requests);
    List<User> records = service.findAll();
    assertTrue(records.size() == 2);
  }

  @Test
  public void testGetUser() throws Exception {
    User user = create("345", ROLES[0]);
    when(repository.findById("345")).thenReturn(Optional.of(user));
    User record = service.get("345");
    assertTrue(record != null);
  }

  @Test
  public void testGetUserThrowsExceptionIDIsNotFound() {
    when(repository.findById(any(String.class)))
      .thenReturn(Optional.ofNullable(null));
    Exception exception = assertThrows(
      Exception.class,
      () -> service.get("1234")
    );
    String expectedMessage = "User not found";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testPutUser() throws Exception {
    User old = create("345", ROLES[0]);
    User user = create("345", ROLES[1]);
    when(repository.findByLogin(any(String.class))).thenReturn(Optional.of(old));
    when(repository.save(any(User.class))).thenReturn(user);
    User current = service.put("345", user);
    assertTrue(current != null && current.getName().equals(user.getName()));
  }

  @Test
  public void testAddUser() {
    User user = create("345", ROLES[2]);
    when(repository.save(any(User.class))).thenReturn(user);
    service.add(user);
  }

  @Test
  public void testAddWithArrayParameters() {
    List<User> list1 = Arrays.asList(new User(), new User());
    List<Resource> list2 = Arrays.asList(new Resource(), new Resource());
    List<Department> list3 = Arrays.asList(new Department(), new Department());
    String[][] users = {
      { "n-yusuf", "Nurudeen", "Yusuf", "INITIATOR", "100" },
      { "o-shewu", "Oluwatosin", "Tosin", "REVIEWER", "005" },
    };
    String[][] res = { { "Admin", "users", "Manage Users", "ADMIN" } };
    String[][] depts = {
      { "001", "Asset Management" },
      { "002", "Benefit Processing" },
    };
    when(repository.saveAll(any(List.class))).thenReturn(list1);
    service.add(User.class, users);
    when(resources.saveAll(any(List.class))).thenReturn(list2);
    service.add(Resource.class, res);
    when(departments.saveAll(any(List.class))).thenReturn(list3);
    service.add(Department.class, depts);
  }

  @Test
  public void testAddResource() {
    Resource r = new Resource();
    r.setId("view");
    when(resources.save(any(Resource.class))).thenReturn(r);
    service.add(r);
  }

  @Test
  public void testAddDepartment() {
    Department r = new Department();
    r.setCode("001");
    when(departments.save(any(Department.class))).thenReturn(r);
    service.add(r);
  }

  @Test
  public void testPutDepartment() throws Exception {
    Department old = new Department("001", "Test");
    Department dept = new Department("001", "IT");
    when(departments.getOne(any(String.class))).thenReturn(old);
    when(departments.save(any(Department.class))).thenReturn(dept);
    Department current = service.put("001", dept);
    assertTrue(current != null && current.getName().equals(dept.getName()));
  }

  @Test
  public void testHasRoleWhenUserNameIsNull() throws Exception {
    final String LINK = "/statements/search";
    boolean result = service.hasRole(null, LINK);
    assertTrue(!result);
  }

  @Test
  public void testHasRole() throws Exception {
    final String LINK = "/statements/search";
    User user = create("0001", "TEST");
    Resource r = new Resource();
    r.setLink("/statements/search");
    r.setPriviledge("TEST");
    when(repository.findByLogin(any(String.class))).thenReturn(Optional.of(user));
    when(resources.findDistinctByLink(any(String.class))).thenReturn(Optional.of(r));
    service.hasRole("0001", LINK);
  }

  @Test
  public void testHasRoleFailedIfResourceRoleDoesNotMatch() throws Exception {
    final String LINK = "/statements/search";
    User user = create("0001", "INITIATOR");
    Resource r = new Resource();
    r.setLink("/statements/search");
    r.setPriviledge("AUTHORIZER");
    when(repository.findByLogin(any(String.class))).thenReturn(Optional.of(user));
    when(resources.findDistinctByLink(any(String.class))).thenReturn(Optional.of(r));
    boolean result = service.hasRole("0001", LINK);
    assertTrue(result == false);
  }

  @Test
  public void testHasRoleFailedForUnknownResource() throws Exception {
    final String LINK = "/statements/search";
    User user = create("0001", "INITIATOR");
    when(repository.findByLogin(any(String.class))).thenReturn(Optional.of(user));
    when(resources.findDistinctByLink(any(String.class))).thenReturn(Optional.ofNullable(null));
    Exception exception = assertThrows(
      InvalidAccessError.class,
      () -> service.hasRole("0001", LINK)
    );
    String expectedMessage = "Unknown resource";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void testFindAllDepartments() {
    List<Department> requests = new ArrayList<>();
    requests.add(new Department());
    requests.add(new Department());
    when(departments.findAll()).thenReturn(requests);
    List<Department> records = service.findAllDepartments();
    assertTrue(records.size() == 2);
  }

  @Test
  public void testGetDepartment() throws Exception {
    Department d = new Department("345", "IT");
    when(departments.getOne("345")).thenReturn(d);
    Department record = service.getDepartment("345");
    assertTrue(record != null && record.getName().equals("IT"));
  }

  private User create(String id, String role) {
    return new User(id, "Nurudeen", "Yusuf", role, "100");
  }
}
