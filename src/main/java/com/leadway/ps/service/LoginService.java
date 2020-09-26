package com.leadway.ps.service;

import com.leadway.ps.model.Credentials;
import com.leadway.ps.model.User;
import com.leadway.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dev.io
 */

@Service
public class LoginService {
  UserService users;

  @Autowired
  public LoginService(UserService userService) {
    this.users = userService;
  }

  public boolean authenticate(Credentials credentials) throws Exception{
    if (
      credentials.getPassword() == null || credentials.getUsername() == null
    ) {
      return false;
    }
    if (users.find(credentials.getUsername()) == null) return false;
    return credentials.getPassword().equalsIgnoreCase("pass");
  }
}
