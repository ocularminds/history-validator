package com.leadway.ps;

import com.leadway.ps.model.Department;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
import com.leadway.ps.service.UserService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Initial data loader
 */
@Component
public class Loader implements ApplicationRunner {
  private final UserService users;

  @Autowired
  public Loader(UserService userService) {
    this.users = userService;
  }

  @Override
  public void run(ApplicationArguments aa) throws Exception {
    if (this.users.findAll().isEmpty()) {
      System.out.println("--- Populating  records ----");
      System.out.println("Please note that this should come from database");
      String[][] depts = {
        { "001", "Asset Management" },
        { "002", "Benefit Processing" },
        { "003", "Business Process Improvement" },
        { "004", "Customer Experience" },
        { "005", "Customer Relationship Management" },
        { "006", "Compliance" },
        { "007", "Executive Management" },
        { "008", "Finance/Admin" },
        { "009", "Human Resources" },
        { "100", "Information Technology" },
        { "101", "Internal Audit and Control" },
        { "102", "Legal and Company Secretariat" },
        { "103", "Operations" },
        { "104", "Risk Management & Strategy" },
      };
      System.out.println("--- 1. Populating department records ----");
      this.users.add(Department.class, depts);
      String[][] res = {
        { "Admin", "users", "Manage Users", "ADMIN" },
        { "Admin", "roles", "Manage Users", "ADMIN" },
        { "Statements", "statements/search", "Generate", "INITIATOR" },
        { "Statements", "statements/reviews", "Review", "REVIEWER" },
        { "Statements", "statements/approvals", "Approvals", "AUTHORIZER" },
      };
      System.out.println("--- 2. Populating resources[menu] records ----");
      this.users.add(Resource.class, res);
      System.out.println("--- 3. Populating user records ----");
      String[][] appusers = {
        { "n-yusuf", "Nurudeen", "Yusuf", "INITIATOR", "100" },
        { "o-shewu", "Oluwatosin", "Tosin", "REVIEWER", "005" },
        { "o-aliu", "Olayinka", "Aliu", "AUTHORIZER", "101" },
      };
      users.add(User.class, appusers);
      System.out.println(
        "--- Populating completed. " + users.findAll().size() + " records"
      );
    }
  }
}
