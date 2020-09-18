package com.leadway.ps.service;

import com.leadway.ps.ExcelFile;
import com.leadway.ps.QueryBuilder;
import com.leadway.ps.RecordRowMapper;
import com.leadway.ps.StatementRowMapper;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.StatementRequest;
import com.leadway.ps.repository.HistoryRepository;
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

/**
 *
 * @author Dev.io
 */
@Service
public class StatementService {
  List<StatementRequest> requests;
  private JdbcTemplate template;
  private HistoryRepository repository;

  @Autowired
  public StatementService(
    JdbcTemplate jdbcTemplate,
    HistoryRepository historyRepository
  ) {
    this.repository = historyRepository;
    requests = new ArrayList<>();
    this.template = jdbcTemplate;
    if (this.template != null) {
      this.template.setResultsMapCaseInsensitive(true);
    }
  }

  public List<StatementRequest> findAll() {
    return repository.findAll();
  }

  public StatementRequest search(String pin) {
    List<StatementRequest> list = template.query(
      QueryBuilder.search(),
      new Object[] { pin },
      new StatementRowMapper()
    );
    return list.size() > 0 ? list.get(0) : null;
  }

  public List<StatementRequest> search(Criteria criteria, String user) {
    List<StatementRequest> data = new ArrayList<>();
    if (
      criteria.getPin() == null || criteria.getPin().trim().isEmpty()
    ) return data;
    StatementRequest req = search(criteria.getPin());
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

  public List<StatementRequest> findAllPending() {
    return repository.findAllByStatus("PENDING");
  }

  public List<StatementRequest> findAllReviewed() {
    return repository.findAllByStatus("REVIEWED");
  }

  public StatementRequest getStatement(String pin) throws Exception {
    return repository
      .findById(pin)
      .orElseThrow(() -> new Exception("Entry not found"));
  }

  public void approve(Approval approval) throws Exception {
    StatementRequest req = getStatement(approval.getRequestId());
    req.setStatus(approval.getApproval().name());
    req.getComments().add(approval.getComment());
    repository.save(req);
  }

  private void addStatistics(StatementRequest req, List<Record> data) {
    req.setPrice(new BigDecimal("3.7230"));
    BigDecimal netSum = BigDecimal.ZERO, totalSum = BigDecimal.ZERO;
    BigDecimal unitSum = BigDecimal.ZERO;
    int max = data.size();
    BigDecimal debits, total, net;
    List<Record> records = new ArrayList<>();
    Record record;
    for (int j = 0; j < max; j++) {
      record = data.get(j);
      unitSum = unitSum.add(req.getUnits());
      netSum = netSum.add(record.getNet());
      totalSum = totalSum.add(record.getTotal());
      record.setId(req.getPin() + record.getPfa() + j);
      records.add(record);
    }

    req.setUnits(unitSum);
    req.setBalance(
      unitSum.multiply(req.getPrice())
      .setScale(2, RoundingMode.HALF_UP)
    );
    req.setEarning(req.getBalance().subtract(netSum));
    req.setRecords(records);
  }
}
