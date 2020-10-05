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
import java.text.SimpleDateFormat;
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
      req = repository.save(req);
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
    return repository.findAllByStatus("REVIEW");
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
	String s1 = Integer.toString((int) ((Math.random() * 800) + 100));
	String s2 = Long.toString(System.currentTimeMillis());
	String s3 = Integer.toString((int) ((Math.random() * 8000) + 1000));
	s1 = s1 + "-"+ new StringBuilder(s2).reverse().substring(0, 6)+"-"+s3;
	Date date = new Date();
	String Q1 = new SimpleDateFormat("yyyy").format(date);
	String Q = new SimpleDateFormat("MM").format(date);
	int D = Integer.parseInt(Q)/3;
	int R = Integer.parseInt(Q) % 3;
	D = R > 0 ? D + 1: D;
	Q = Integer.toString(D)+"-"+Q1;
	Q = "3-2020";
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
      record.setSerial(j+1);
      record.setQuarter(Q);
      record.setReference(s1);
      record.setStatement(req);
      records.add(record);
    }
    unitSum = req.getBalance().divide(new BigDecimal(req.getPrice()), 2, RAND);
    req.setReference(s1);
    req.setQuarter(Q);
    req.setUnits(unitSum.setScale(2, RAND));
    req.setEarning(req.getBalance().subtract(netSum).setScale(2, RAND));
    req.setRecords(records);
  }
}
