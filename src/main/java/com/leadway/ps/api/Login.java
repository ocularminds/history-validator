package com.leadway.ps.api;

/**
 *
 * @author Dev.io
 */
import com.leadway.ps.model.Credentials;
import com.leadway.ps.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class Login {

    @Autowired
    LoginService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndex(ModelMap model) {
        model.put("credentials", new Credentials());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("credentials", new Credentials());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String showLogoutPage(ModelMap model) {
        model.clear();
        return "redirect:/login";
    }

    @PostMapping(value = "/login")
    public String authenticate(ModelMap model, @ModelAttribute(value="credentials") Credentials credentials, BindingResult result) {
        boolean isValidUser = service.authenticate(credentials);
        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        model.put("username", credentials.getUsername());
        model.put("name","Yusuf");
        model.put("fullname","Yusuf Nurudeen"); 
        return "dashboard";
    }

}
