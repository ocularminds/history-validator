package com.leadway.ps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.Statement;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

public class ExcelFileTest {

  @Test
  public void testToFile() throws Exception {
    String msg = "File production successful";
    Statement statement = createRequest("PSFX0000001");
    List<Record> records = randomise(10);
    statement.setRecords(records);
    ExcelFile excel = new ExcelFile(statement);
    Fault fault = excel.toFile();
    assertTrue(fault != null && fault.getError().equals("00"));
    assertTrue(fault != null && fault.getFault().equals(msg));
  }

  private Statement createRequest(String pin) {
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
    req.setFirstName(emp[0]);
    req.setSurname(emp[1]);
    req.setMiddleName(emp[2]);
    req.setPin(pin);
    req.setEmployer("PR0000070199");
    req.setCode("FUND3");
    req.setPrice("3.7230");
    req.setFundId("456345000");
    BigDecimal fundUnits = new BigDecimal(Math.random() * 99999999)
    .setScale(2, RoundingMode.HALF_UP);
    req.setUnits(fundUnits);
    req.setEarning(fundUnits);
    req.setBalance(fundUnits);
    req.setStatus("PENDING");
    return req;
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
