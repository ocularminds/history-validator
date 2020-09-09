package com.leadway.ps;

import com.leadway.ps.model.Department;
import com.leadway.ps.model.Resource;
import com.leadway.ps.model.User;
import com.leadway.ps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Initial data loader
 */
@Component
public class Loader implements ApplicationRunner {
  private UserService users;

  @Autowired
  public Loader(UserService userService) {
    this.users = userService;
  }

  public void run(ApplicationArguments args) {
    if (this.users.findAll().isEmpty()) {
      System.out.println("--- Populating user records ----");
      System.out.println("Please note that this should come from database");
      User user;
      Department department;
      Resource resource;
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
      for (String[] d : depts) {
        this.users.add(new Department(d[0], d[1]));
      }
      String[][] res = {
        { "Admin", "users", "Manage Users", "ADMIN" },
        { "Admin", "roles", "Manage Users", "ADMIN" },
        { "Statements", "statements/search", "Generate", "INITIATOR" },
        { "Statements", "statements/reviews", "Review", "REVIEWER" },
        { "Statements", "statements/approvals", "Approvals", "AUTHORIZER" },
      };
      for (String[] r : res) {
        this.users.add(new Resource(r[0], r[1], r[2], r[3]));
      }
      String[][] appusers = {
        { "n-yusuf", "Nurudeen", "Yusuf", "INITIATOR", "100" },
        { "a-ayodeji", "Adeotun", "Owoyemi", "REVIEWER", "005" },
        { "a-omogbe", "Adeotun", "Omogbemi", "AUTHORIZER", "101" },
      };
      for (String[] u : appusers) {
        this.users.add(new User(u[0], u[1], u[2], u[3], u[4]));
      }
      System.out.println(
        "--- Populating completed. " + this.users.findAll().size() + " records"
      );
    }
  }
}
