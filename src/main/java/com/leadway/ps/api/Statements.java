package com.leadway.ps.api;

import com.leadway.ps.ExcelFile;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.StatementRequest;
import com.leadway.ps.service.StatementService;
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
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"username", "greetings", "fullname"})
@RequestMapping("/statements")
public class Statements {

    StatementService statements;

    @Autowired
    public Statements(StatementService statements) {
        this.statements = statements;
    }

    @RequestMapping("/requests")
    public String getAll(ModelMap model) {
        String username = (String) model.get("username");
        List<StatementRequest> requests = statements.findAll();
        System.out.println("Total requests: " + requests.size());
        model.put("statements", requests);
        return "requests";
    }

    @RequestMapping("/requests/{id}")
    public String getStatement(@PathVariable(value = "id") String id, ModelMap model) throws Exception {
        String name = (String) model.get("username");
        StatementRequest r = statements.getStatement(id);
        model.put("statement", r);
        new ExcelFile(r).toFile();
        return "request-details";
    }

    @RequestMapping("/export/{pin}")
    public void download(@PathVariable(value = "pin") String pin, HttpServletResponse response) throws InterruptedException, ExecutionException {
        String fn = pin + ".xlsx";
        String file = ExcelFile.FOLDER + File.separator + fn;
        String attachement = String.format("attachment; filename=\"%s\"", fn);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentLengthLong(file.length());
        response.addHeader("Content-Disposition", attachement);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> future = executor.submit(() -> {
            try (ServletOutputStream os = response.getOutputStream();
                    FileInputStream in = new FileInputStream(file)) {
                IOUtils.copy(in, os);
                os.flush();
            }
            return null;
        });
        future.get();
        executor.shutdown();
    }

    @RequestMapping("/search")
    public String search(ModelMap model) {
        String username = (String) model.get("username");
        model.put("statements", Collections.EMPTY_LIST);
        model.put("criteria", new Criteria());
        return "search";
    }

    @PostMapping("/search")
    public String search(ModelMap model, @ModelAttribute(value = "criteria") Criteria criteria) {
        String username = (String) model.get("username");
        List<StatementRequest> requests = statements.search(criteria, username);
        model.put("statements", requests);
        return "search";
    }

    @RequestMapping("/reviews")
    public String getReviewables(ModelMap model) {
        String username = (String) model.get("username");
        List<StatementRequest> requests = statements.findAll();
        System.out.println("Total requests: " + requests.size());
        model.put("statements", requests);
        return "reviews";
    }

    @RequestMapping("/reviews/{id}")
    public String getReviewable(@PathVariable(value = "id") String id, ModelMap model) throws Exception {
        String name = (String) model.get("name");
        model.put("statement", statements.getStatement(id));
        return "review";
    }

    @PostMapping("/approve/{id}")
    public String review(@PathVariable(value = "id") String id, ModelMap model, Approval approval) throws Exception {
        String username = (String) model.get("username");
        statements.approve(approval);
        model.put("statement", statements.getStatement(id));
        return "review";
    }
}
