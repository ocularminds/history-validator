package com.leadway.ps.api;

import com.leadway.ps.ExcelFile;
import com.leadway.ps.InvalidAccessError;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Statement;
import com.leadway.ps.service.StatementService;
import com.leadway.ps.service.UserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = { "username", "greetings", "fullname" })
@RequestMapping("/statements")
public class Statements {
  StatementService statements;
  UserService users;

  @Autowired
  public Statements(StatementService statements, UserService users) {
    this.statements = statements;
    this.users = users;
  }

  @ExceptionHandler(InvalidAccessError.class)
  public String handleAnyException(Throwable ex, HttpServletRequest request) {
    return "denied";
  }

  @RequestMapping("/requests")
  public String getAll(ModelMap model) {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    List<Statement> requests = statements.findAll();
    model.put("statements", requests);
    return "requests";
  }

  @RequestMapping("/requests/{id}")
  public String getStatement(
    @PathVariable(value = "id") String id,
    ModelMap model
  )
    throws Exception {
    String name = (String) model.get("username");
    if (name == null) return "redirect:/login";
    Statement r = statements.getStatement(id);
    model.put("statement", r);
    new ExcelFile(r).toFile();
    return "records";
  }

  @RequestMapping("/export/{pin}")
  public void download(
    @PathVariable(value = "pin") String pin,
    HttpServletResponse response
  )
    throws InterruptedException, ExecutionException, IOException {
    String fn = pin + ".xlsx";
    String file = ExcelFile.FOLDER + File.separator + fn;
    String attachement = String.format("attachment; filename=\"%s\"", fn);
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    response.setContentLengthLong(file.length());
    response.addHeader("Content-Disposition", attachement);
    writeFile(response, file);
  }

  @RequestMapping("/search")
  public String search(ModelMap model) throws InvalidAccessError, Exception {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    if (!users.hasRole(username, "statements/search")) {
      throw new InvalidAccessError(
        "You are not authorized to access this recource"
      );
    }
    model.put("statements", Collections.EMPTY_LIST);
    model.put("criteria", new Criteria());
    return "search";
  }

  @PostMapping("/search")
  public String search(
    ModelMap model,
    @ModelAttribute(value = "criteria") Criteria criteria
  ) {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    List<Statement> requests = statements.search(criteria, username);
    model.put("statements", requests);
    return "search";
  }

  @RequestMapping("/reviews")
  public String getReviewables(ModelMap model)
    throws InvalidAccessError, Exception {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    if (!users.hasRole(username, "statements/reviews")) {
      throw new InvalidAccessError(
        "You are not authorized to access this recource"
      );
    }
    List<Statement> requests = statements.findAllPending();
    model.put("statements", requests);
    return "reviews";
  }

  @RequestMapping("/reviews/{id}")
  public String getReviewable(
    @PathVariable(value = "id") String id,
    ModelMap model
  )
    throws Exception {
    String name = (String) model.get("name");
    if (name == null) return "redirect:/login";
    if (!users.hasRole(name, "statements/reviews")) {
      throw new InvalidAccessError(
        "You are not authorized to access this recource"
      );
    }
    model.put("statement", statements.getStatement(id));
    model.put("endpoint", "/statements/reviews/"+id);    
    model.put("buttonLabel", "Pass");
    model.put("approval", new Approval(id, Approval.ApprovalType.REVIEW));
    return "records";
  }

  @PostMapping("/reviews/{id}")
  public String review(
    @PathVariable(value = "id") String id,
    ModelMap model,
    Approval approval
  )
    throws Exception {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    if (!users.hasRole(username, "statements/reviews")) {
      throw new InvalidAccessError(
        "You are not authorized to access this recource"
      );
    }
    statements.approve(approval);
    model.put("statement", statements.getStatement(id));
    return "review";
  }

  @RequestMapping("/approvals")
  public String getApprovables(ModelMap model)
    throws InvalidAccessError, Exception {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    if (!users.hasRole(username, "statements/approvals")) {
      throw new InvalidAccessError(
        "You are not authorized to access this resource"
      );
    }
    List<Statement> requests = statements.findAllReviewed();
    model.put("statements", requests);
    return "approvals";
  }

  @RequestMapping("/approvals/{id}")
  public String getApprovable(
    @PathVariable(value = "id") String id,
    ModelMap model
  )
    throws Exception {
    String name = (String) model.get("name");
    if (name == null) return "redirect:/login";
    if (!users.hasRole(name, "statements/approvals")) {
      throw new InvalidAccessError(
        "You are not authorized to access this recource"
      );
    }
    model.put("statement", statements.getStatement(id));
    model.put("endpoint", "statements/approvals/"+id);    
    model.put("buttonLabel", "Approve");
    model.put("approval", new Approval(id, Approval.ApprovalType.APPROVE));
    return "records";
  }

  @PostMapping("/approvals/{id}")
  public String approve(
    @PathVariable(value = "id") String id,
    ModelMap model,
    Approval approval
  )
    throws Exception {
    String username = (String) model.get("username");
    if (username == null) return "redirect:/login";
    if (!users.hasRole(username, "statements/approvals")) {
      throw new InvalidAccessError(
        "You are not authorized to access this recource"
      );
    }
    statements.approve(approval);
    model.put("statement", statements.getStatement(id));
    return "records";
  }

  private void writeFile(HttpServletResponse response, String file) {
    ServletOutputStream os = null;
    FileInputStream in = null;
    try {
      os = response.getOutputStream();
      in = new FileInputStream(file);
      IOUtils.copy(in, os);
      os.flush();
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (in != null) in.close();
        if (os != null) os.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
