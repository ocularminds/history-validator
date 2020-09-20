package com.leadway.ps.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.leadway.ps.model.Credentials;
import com.leadway.ps.model.User;
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
public class LoginServiceTest {
  @Mock
  UserService users;

  @InjectMocks
  LoginService service;

  @Test
  public void testLogin() throws Exception {
    Credentials credentials = new Credentials();
    credentials.setUsername("3244");
    credentials.setPassword("pass");
    when(users.get(any(String.class))).thenReturn(new User());
    boolean result = service.authenticate(credentials);
    assertTrue(result);
  }

  @Test
  public void testAuthenticateWithUnknownUser() throws Exception {
    Credentials credentials = new Credentials();
    credentials.setUsername("3244");
    credentials.setPassword("pass");
    when(users.get(any(String.class))).thenReturn(null);
    boolean result = service.authenticate(credentials);
    assertTrue(!result);
  }

  @Test
  public void testLoginFailedWhenPasswordIsNotMatch() throws Exception {
    Credentials credentials = new Credentials();
    credentials.setUsername("3244");
    credentials.setPassword("olodo");
    when(users.get(any(String.class))).thenReturn(new User());
    boolean result = service.authenticate(credentials);
    assertTrue(!result);
  }

  @Test
  public void testLoginFailedWhenUserNameAndPasswordIsNull() throws Exception {
    Credentials credentials = new Credentials();
    boolean result = service.authenticate(credentials);
    assertTrue(!result);
    credentials.setUsername("");
    result = service.authenticate(credentials);
    assertTrue(!result);
    credentials.setUsername(null);
    credentials.setPassword("");
    result = service.authenticate(credentials);
    assertTrue(!result);
  }
}
