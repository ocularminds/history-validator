package com.leadway.ps.service;

import com.leadway.ps.ExcelFile;
import com.leadway.ps.QueryBuilder;
import com.leadway.ps.RecordRowMapper;
import com.leadway.ps.StatementRowMapper;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.Statement;
import com.leadway.ps.repository.StatementRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Babatope Festus
 */
@Service
public class StatementService {
  List<Statement> requests;
  private JdbcTemplate template;
  private StatementRepository repository;

  @Autowired
  public StatementService(
    JdbcTemplate jdbcTemplate,
    StatementRepository statementRepository
  ) {
    this.repository = statementRepository;
    requests = new ArrayList<>();
    this.template = jdbcTemplate;
    this.template.setResultsMapCaseInsensitive(true);
  }

  public List<Statement> findAll() {
    return repository.findAll();
  }

  @Transactional
  public Statement search(String pin) {
    List<Statement> list = template.query(
      QueryBuilder.search(),
      new Object[] { pin },
      new StatementRowMapper()
    );
    return list.size() > 0 ? list.get(0) : null;
  }

  @Transactional
  public List<Statement> search(Criteria criteria, String user) {
    List<Statement> data = new ArrayList<>();
    if (
      criteria.getPin() == null || criteria.getPin().trim().isEmpty()
    ) return data;
    Statement req = search(criteria.getPin());
    if (req == null) {
      return data;
    }
    req.setRequester(user);
    try {
      List<Record> records = template.query(
        QueryBuilder.build(),
        new Object[] { criteria.getPin(), criteria.getPin() },
        new RecordRowMapper()
      );

      if (records == null || records.isEmpty()) {
        return data;
      }
      addStatistics(req, records);
      System.out.println("Total records in search: " + req.getRecords().size());
      req = repository.save(req);
      System.out.println("Total after saving data: " + req.getRecords().size());
      data.add(req);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }

    return data;
  }

  public List<Statement> findAllPending() {
    return repository.findAllByStatus("PENDING");
  }

  public List<Statement> findAllReviewed() {
    return repository.findAllByStatus("REVIEWED");
  }

  public Statement getStatement(String pin) throws Exception {
    return repository
      .findById(pin)
      .orElseThrow(() -> new Exception("Entry not found"));
  }

  @Transactional
  public void approve(Approval approval) throws Exception {
    Statement req = getStatement(approval.getRequestId());
    req.setStatus(approval.getApproval().name());
    req.getComments().add(approval.getComment());
    repository.save(req);
  }

  private void addStatistics(Statement req, List<Record> data) {
    BigDecimal netSum = BigDecimal.ZERO, totalSum = BigDecimal.ZERO;
    BigDecimal unitSum = BigDecimal.ZERO;
    RoundingMode RAND = RoundingMode.HALF_UP;
    int max = data.size();
    BigDecimal debits, total, net;
    List<Record> records = new ArrayList<>();
    Record record;
    for (int j = 0; j < max; j++) {
      record = data.get(j);
      unitSum = unitSum.add(req.getUnits().setScale(2, RAND));
      netSum = netSum.add(record.getNet().setScale(2, RAND));
      totalSum = totalSum.add(record.getTotal().setScale(2, RAND));
      record.setId(req.getPin() + record.getPfa() + j);
      record.setStatement(req);
      records.add(record);
    }

    req.setUnits(unitSum);
    req.setBalance(unitSum.multiply(req.getPrice()).setScale(2, RAND));
    req.setEarning(req.getBalance().subtract(netSum).setScale(2, RAND));
    req.setRecords(records);
  }
}
