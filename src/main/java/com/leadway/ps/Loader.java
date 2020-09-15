package com.leadway.ps;

import com.leadway.ps.model.Department;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
import com.leadway.ps.service.UserService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
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
      for (String[] d : depts) {
        System.out.println("--- \t"+Arrays.toString(d));
        this.users.add(new Department(d[0], d[1]));
      }
      String[][] res = {
        { "Admin", "users", "Manage Users", "ADMIN" },
        { "Admin", "roles", "Manage Users", "ADMIN" },
        { "Statements", "statements/search", "Generate", "INITIATOR" },
        { "Statements", "statements/reviews", "Review", "REVIEWER" },
        { "Statements", "statements/approvals", "Approvals", "AUTHORIZER" },
      };
      System.out.println("--- 2. Populating resources[menu] records ----");
      for (String[] r : res) {
        System.out.println("--- \t"+Arrays.toString(r));
        this.users.add(new Resource(r[0], r[1], r[2], r[3]));
      }
      System.out.println("--- 3. Populating user records ----");
      String[][] appusers = {
        { "n-yusuf", "Nurudeen", "Yusuf", "INITIATOR", "100" },
        { "o-shewu", "Oluwatosin", "Tosin", "REVIEWER", "005" },
        { "o-aliu", "Olayinka", "Aliu", "AUTHORIZER", "101" },
      };
      for (String[] u : appusers) {
        System.out.println("--- \t"+Arrays.toString(u));
        this.users.add(new User(u[0], u[1], u[2], u[3], u[4]));
      }
      System.out.println(
        "--- Populating completed. " + this.users.findAll().size() + " records"
      );
    }
  }
}
