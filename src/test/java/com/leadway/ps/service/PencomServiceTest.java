package com.leadway.ps.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.leadway.ps.RecordRowMapper;
import com.leadway.ps.StatementRowMapper;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.Statement;
import com.leadway.ps.repository.StatementRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class PencomServiceTest {

  @Mock
  StatementRepository repository;

  @InjectMocks
  PencomService service;

  @Test
  public void testSubmit() {
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
	Q = "3-2020";
    Statement request = createRequest("PEN100000000001",Q,s1);
    List<Record> records = randomise(2,Q,s1);
    request.setRecords(records);
    service.submit(request);
  }


  private Statement createRequest(String pin, String Q, String ref) {
    Statement req = new Statement();
    String[] names = {
      "Akpan, Jinadu, Paul",
      "Segun,Hammed, Bello",
      "Chukwu,Festus,Adimeru",
      "Omosetan,Omorele,Pius",
      "Djeri,Oriola,Yakub",
    };
    int random = (int) (Math.random() * 5);
    String[] emp = names[random].split(",");
    req.setFundId("3454000");
    req.setQuarter(Q);
    req.setReference(ref);
    req.setFirstName(emp[0]);
    req.setSurname(emp[1]);
    req.setMiddleName(emp[2]);
    req.setPin(pin);
    req.setEmployer("PR0000111100");
    req.setFundId("FUND4");
	req.setCode("023");
    req.setPrice("3.8504");
    BigDecimal fundUnits = new BigDecimal(Math.random() * 99999999)
    .setScale(2, RoundingMode.HALF_UP);
    req.setUnits(fundUnits);
    req.setStatus("PENDING");
    return req;
  }

  private List<Record> randomise(int max,String Q, String ref) {
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
      record.setVoluntaryContigent(BigDecimal.ZERO.setScale(2, RAND));
      record.setVoluntaryRetirement(BigDecimal.ZERO.setScale(2, RAND));
      record.setOtherInflows(BigDecimal.ZERO.setScale(2, RAND));
      total = record.getEmployer().add(record.getContribution().setScale(2, RAND));
      total = total.add(record.getVoluntaryContigent());
      total = total.add(record.getVoluntaryRetirement());
      total = total.add(record.getOtherInflows());
      units = total.divide(new BigDecimal("0.455"), 2, RAND);
      record.setTotal(total);
      record.setUnits(units);
      record.setFees(new BigDecimal("100.00"));
      record.setWithdrawals(debits.setScale(2, RAND));
      net = total.subtract(record.getFees()).subtract(debits);
      netSum = netSum.add(net);
      record.setSerial(j+1);
	  record.setQuarter(Q);
      record.setReference(ref);
      record.setNet(net);
	  record.setPfa("023");
      records.add(record);
    }
    return records;
  }
}
