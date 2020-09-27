package com.leadway.ps.api;

/**
 *
 * @author Dev.io
 */
import com.leadway.ps.model.Credentials;
import com.leadway.ps.model.User;
import com.leadway.ps.service.LoginService;
import com.leadway.ps.service.UserService;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"username", "greetings", "fullname"})
public class Login {
    
    final LoginService service;
    final UserService users;

    @Autowired
    public Login(LoginService loginService,UserService userService){
this.service = loginService;
this.users=userService;
    }

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

    @GetMapping(value = "/dashboard")
    public String dashboard(ModelMap model) throws Exception{
        String username = (String) model.get("username");
        if(username == null) return "redirect:/login";
        User user = users.find(username);
        String greetings;
        int time = Integer.parseInt(new SimpleDateFormat("hh").format(new Date()));
        if (time < 12) {
            greetings = "Morning";
        } else if (time >= 12 && time < 18) {
            greetings = "Afternoon";
        } else {
            greetings = "Evening";
        }
        model.put("username", username);
        model.put("greetings", "Good " + greetings + " "+user.getName()+"!");
        model.put("fullname", user.getSurname()+" "+user.getName());
        return "dashboard";
    }

    @PostMapping(value = "/login")
    public String authenticate(ModelMap model, @ModelAttribute(value = "credentials") Credentials credentials, 
    BindingResult result)throws Exception {
        boolean isValidUser = service.authenticate(credentials);
        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }
        model.put("username", credentials.getUsername());
        return "redirect:/dashboard";
    }

}
