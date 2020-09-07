package com.leadway.ps.service;

import com.leadway.ps.ExcelFile;
import com.leadway.ps.RecordRowMapper;
import com.leadway.ps.StatementRowMapper;
import com.leadway.ps.model.Approval;
import com.leadway.ps.model.Criteria;
import com.leadway.ps.model.Record;
import com.leadway.ps.model.StatementRequest;
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
    private SimpleJdbcCall procedure;

    @Autowired
    public StatementService(JdbcTemplate jdbcTemplate, SimpleJdbcCall simpleJdbcCall) {
        requests = new ArrayList<>();
        this.template = jdbcTemplate;
        this.procedure = simpleJdbcCall;
        if (this.template != null) {
            this.template.setResultsMapCaseInsensitive(true);
        }
        if (this.procedure != null) {
            this.procedure = simpleJdbcCall.withProcedureName("uspTypeAStatementTransactions")
                    .returningResultSet("statements", new RecordRowMapper());
        }
    }

    public List<StatementRequest> findAll() {
        return requests;
    }

    public StatementRequest search(String pin) {
        StringBuilder sb = new StringBuilder();
        sb.append("select e.firstname,e.lastname,e.MiddleName,e.rsapin,e.fundid, ");
        sb.append("re.employercode, fd.FundName from employee join employer er ");
        sb.append("on e.employerid = er.employerid join  funddefinition fd ");
        sb.append("on fd.funddefinitionid = e.fundid and e.rsapin = ?");
        List<StatementRequest> list = template.query(
                sb.toString(), new Object[]{pin}, new StatementRowMapper()
        );
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<StatementRequest> create(Criteria criteria, String user) {
        List<StatementRequest> searched = randomise(criteria);
        requests.addAll(searched);
        return searched;
    }

    public List<StatementRequest> search(Criteria criteria, String user) {
        List<StatementRequest> data = new ArrayList<>();
        StatementRequest req = search(criteria.getPin());
        if (req == null) {
            return data;
        }
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("FundID", req.getFundId())
                .addValue("RSAPIN", criteria.getPin())
                .addValue("FromDate", criteria.getFrom())
                .addValue("ToDate", criteria.getTo());

        try {           
            Map<String, Object> out = procedure.execute(in);
            if (out == null) {
                data.add(req);
                return data;
            }
            List<Record> records = (List<Record>) out.get("statements");
            addCalculatedValues(req, records);
            data.add(req);
            requests.addAll(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }

        return data;

    }

    public List<StatementRequest> selectForReview() {
        List<StatementRequest> filtered = new ArrayList<>();
        requests.stream().filter((req) -> (req.getStatus().equals("PENDING"))).forEachOrdered((req) -> {
            filtered.add(req);
        });
        return filtered;
    }

    public List<StatementRequest> selectForApproval() {
        List<StatementRequest> filtered = new ArrayList<>();
        requests.stream().filter((req) -> (req.getStatus().equals("REVIEWED"))).forEachOrdered((req) -> {
            filtered.add(req);
        });
        return filtered;
    }

    public StatementRequest getStatement(String pin) throws Exception {
        for (StatementRequest e : requests) {
            if (e.getPin().equals(pin)) {
                return e;
            }
        }
        throw new Exception("Entry not found");
    }

    public void approve(Approval approval) throws Exception {
        StatementRequest req = getStatement(approval.getRequestId());
        req.setStatus(approval.getApproval().name());
        req.getComments().add(approval.getComment());
        update(approval.getRequestId(), req);
    }

    public void update(String id, StatementRequest req) {
        for (StatementRequest e : requests) {
            if (e.getPin().equals(id)) {
                int index = requests.indexOf(e);
                requests.remove(e);
                requests.add(index, req);
                return;
            }
        }
        requests.add(0, req);
    }

    public List<StatementRequest> randomise(Criteria criteria) {
        int random;
        int rand = (int) (Math.random() * 25);
        long time = new Date().getTime();
        List<StatementRequest> data = new ArrayList<>();
        List<Record> records;
        Record record;
        String[] names = {
            "Akpan, Jinadu, Paul", "Segun,Hammed, Bello",
            "Chukwu,Festus,Adimeru", "Omosetan,Omorele,Pius", "Djeri,Oriola,Yakub"};
        BigDecimal total, units, fundUnits, net, netSum;
        for (int i = 0; i < rand; i++) {
            random = (int) (Math.random() * 5);
            String[] emp = names[random].split(",");
            StatementRequest req = new StatementRequest();
            req.setFirstName(emp[0]);
            req.setSurname(emp[1]);
            req.setMiddleName(emp[2]);
            req.setPin(criteria.getPin());
            req.setEmployer("PR0000070199");
            req.setCode("FUND3");
            req.setPrice(new BigDecimal("3.7230"));
            netSum = BigDecimal.ZERO;
            fundUnits = new BigDecimal(Math.random() * 99999999).setScale(2, RoundingMode.HALF_UP);
            req.setUnits(fundUnits);
            req.setBalance(fundUnits.multiply(req.getPrice()));
            req.setStatus("PENDING");
            int max = (int) (Math.random() * 25);
            long amount = (int) (Math.random() * 99999999l);
            BigDecimal debits = new BigDecimal((long) (Math.random() * 76543210l));
            records = new ArrayList<>();
            for (int j = 0; j < max; j++) {
                int t = (int) (Math.random() * 1000 * 60 * 60 * 24 * 90);
                record = new Record();
                record.setDateReceived(new Date(time - t));
                record.setMonthStart(new Date(time - t - (1000 * 60 * 60 * 24 * 30)));
                record.setMonthEnd(new Date(time - t - (1000 * 60 * 60 * 24 * 5)));
                record.setType("TF003");
                record.setEmployer(new BigDecimal(amount * 0.44).setScale(2, RoundingMode.HALF_UP));
                record.setContribution(new BigDecimal(amount * 0.56).setScale(2, RoundingMode.HALF_UP));
                record.setVoluntaryContigent(BigDecimal.ZERO);
                record.setVoluntaryRetirement(BigDecimal.ZERO);
                record.setOtherInflows(BigDecimal.ZERO);
                total = record.getEmployer().add(record.getContribution());
                total = total.add(record.getVoluntaryContigent());
                total = total.add(record.getVoluntaryRetirement());
                total = total.add(record.getOtherInflows());
                units = total.divide(req.getPrice(), 2, RoundingMode.HALF_UP);
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

            req.setEarning(req.getBalance().subtract(netSum));
            req.setRecords(records);
            data.add(req);
        }
        return data;
    }

    private void addCalculatedValues(StatementRequest req, List<Record> data) {
        req.setPrice(new BigDecimal("3.7230"));
        BigDecimal netSum = BigDecimal.ZERO, totalSum = BigDecimal.ZERO, unitSum = BigDecimal.ZERO;
        int max = data.size();
        BigDecimal debits, total, net;
        List<Record> records = new ArrayList<>();
        Record record;
        for (int j = 0; j < max; j++) {
            record = data.get(j);
            record.setVoluntaryContigent(BigDecimal.ZERO);
            record.setVoluntaryRetirement(BigDecimal.ZERO);
            record.setOtherInflows(BigDecimal.ZERO);
            total = record.getEmployer().add(record.getContribution());
            total = total.add(record.getVoluntaryContigent());
            total = total.add(record.getVoluntaryRetirement());
            total = total.add(record.getOtherInflows());
            unitSum = unitSum.add(req.getUnits());
            debits = record.getWithdrawals();
            record.setTotal(total);
            net = total.subtract(record.getFees()).subtract(debits);
            netSum = netSum.add(net);
            totalSum = totalSum.add(total);
            record.setNet(net);
            records.add(record);
        }

        req.setUnits(unitSum);
        req.setBalance(unitSum.multiply(req.getPrice()).setScale(2, RoundingMode.HALF_UP));
        req.setEarning(req.getBalance().subtract(netSum));
        req.setRecords(records);
    }

    public static void main(String[] args) throws Exception {
        Criteria criteria = new Criteria(200, "PEN100016997603");
        StatementService service = new StatementService(null, null);
        List<StatementRequest> req = service.randomise(criteria);
        new ExcelFile(req.get(0)).toFile();
    }
}
