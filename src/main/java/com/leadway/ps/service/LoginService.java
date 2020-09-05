package com.leadway.ps.service;

/**
 *
 * @author Dev.io
 */
import com.leadway.ps.model.Credentials;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean authenticate(Credentials credentials) {
        if (credentials.getPassword() == null || credentials.getUsername() == null) {
            return false;
        }
        return credentials.getUsername().equalsIgnoreCase("admin")
                && credentials.getPassword().equalsIgnoreCase("pass");
    }
}
