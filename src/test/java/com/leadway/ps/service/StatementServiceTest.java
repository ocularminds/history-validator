package com.leadway.ps.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.leadway.ps.RecordRowMapper;
import com.leadway.ps.StatementRowMapper;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.StatementRequest;
import com.leadway.ps.repository.HistoryRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

@RunWith(MockitoJUnitRunner.class)
public class StatementServiceTest {
  @Mock
  JdbcTemplate template;

  @Mock
  HistoryRepository repository;

  @InjectMocks
  StatementService service;

  @Test
  public void testFindAll() {
    List<StatementRequest> requests = new ArrayList<>();
    requests.add(new StatementRequest());
    requests.add(new StatementRequest());
    when(repository.findAll()).thenReturn(requests);
    List<StatementRequest> records = service.findAll();
    assertTrue(records.size() == 2);
  }

  @Test
  public void testSearchPin() {
    final List<StatementRequest> requests = new ArrayList<>();
    requests.add(new StatementRequest());
    requests.add(new StatementRequest());
    String pin = "PIN-3424";
    when(
        template.query(
          Mockito.anyString(),
          Mockito.any(Object[].class),
          Mockito.any(StatementRowMapper.class)
        )
      )
      .thenReturn(requests);
    StatementRequest request = service.search(pin);
    assertTrue(request != null);
  }

  @Test
  public void testSearchWithCriteria() {
    String pin = "PIN-3424";
    String user = "dummy";
    final List<StatementRequest> requests = new ArrayList<>();
    StatementRequest req = createRequest(pin);
    List<Record> history = randomise(10);
    req.setRecords(history);
    requests.add(req);
    when(
        template.query(
          any(String.class),
          any(Object[].class),
          any(StatementRowMapper.class)
        )
      )
      .thenReturn(requests);
    when(
        template.query(
          any(String.class),
          any(Object[].class),
          any(RecordRowMapper.class)
        )
      )
      .thenReturn(history);
    when(repository.save(any(StatementRequest.class))).thenReturn(req);
    Criteria criteria = new Criteria();
    criteria.setPin(pin);
    List<StatementRequest> result = service.search(criteria, user);
    assertTrue(result.size() == 1);
  }

  private StatementRequest createRequest(String pin) {
    StatementRequest req = new StatementRequest();
    String[] names = {
      "Akpan, Jinadu, Paul",
      "Segun,Hammed, Bello",
      "Chukwu,Festus,Adimeru",
      "Omosetan,Omorele,Pius",
      "Djeri,Oriola,Yakub",
    };
    int random = (int) (Math.random() * 5);
    String[] emp = names[random].split(",");
    req.setFirstName(emp[0]);
    req.setSurname(emp[1]);
    req.setMiddleName(emp[2]);
    req.setPin(pin);
    req.setEmployer("PR0000070199");
    req.setCode("FUND3");
    req.setPrice(new BigDecimal("3.7230"));
    BigDecimal fundUnits = new BigDecimal(Math.random() * 99999999)
    .setScale(2, RoundingMode.HALF_UP);
    req.setUnits(fundUnits);
    req.setStatus("PENDING");
    return req;
  }

  @Test
  public void testSearchWithCriteriaHavingNullPin() {
    Criteria criteria = new Criteria();
    String user = "dummy";
    criteria.setPin(null);
    List<StatementRequest> result = service.search(criteria, user);
    assertTrue(result.isEmpty());
  }

  private List<Record> randomise(int max) {
    List<Record> records = new ArrayList<>();
    long time = new Date().getTime();
    Record record;
    RoundingMode RAND = RoundingMode.HALF_UP;
    BigDecimal total, units, fundUnits, net, netSum = BigDecimal.ZERO;
    long amount = (int) (Math.random() * 99999999l);
    BigDecimal debits = new BigDecimal((long) (Math.random() * 76543210l));
    for (int j = 0; j < max; j++) {
      int t = (int) (Math.random() * 1000 * 60 * 60 * 24 * 90);
      record = new Record();
      record.setDateReceived(new Date(time - t));
      record.setMonthStart(new Date(time - t - (1000 * 60 * 60 * 24 * 30)));
      record.setMonthEnd(new Date(time - t - (1000 * 60 * 60 * 24 * 5)));
      record.setType("TF003");
      record.setEmployer(new BigDecimal(amount * 0.44).setScale(2, RAND));
      record.setContribution(new BigDecimal(amount * 0.56).setScale(2, RAND));
      record.setVoluntaryContigent(BigDecimal.ZERO);
      record.setVoluntaryRetirement(BigDecimal.ZERO);
      record.setOtherInflows(BigDecimal.ZERO);
      total = record.getEmployer().add(record.getContribution());
      total = total.add(record.getVoluntaryContigent());
      total = total.add(record.getVoluntaryRetirement());
      total = total.add(record.getOtherInflows());
      units = total.divide(new BigDecimal("0.455"), 2, RAND);
      record.setTotal(total);
      record.setUnits(units);
      record.setFees(new BigDecimal("100.00"));
      record.setWithdrawals(debits);
      net = total.subtract(record.getFees()).subtract(debits);
      netSum = netSum.add(net);
      record.setNet(net);
      record.setPfa("022");
      records.add(record);
    }
    return records;
  }
}
