package com.leadway.ps;

import com.leadway.ps.model.Record;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Dev.io
 */
public class RecordRowMapper implements RowMapper<Record> {

    @Override
    public Record mapRow(ResultSet rs, int i) throws SQLException {
        Record record = new Record();
        record.setDateReceived(rs.getDate("PAY_RECEIVE_DATE"));
        record.setMonthStart(rs.getDate("RELATED_MONTH_START"));
        record.setMonthEnd(rs.getDate("RELATED_MONTH_END"));
        record.setType(rs.getString("TRANSACTION_TYPE"));
        record.setEmployer(rs.getBigDecimal("EMPLOYER_CONTRIBUTION"));
        record.setContribution(rs.getBigDecimal("EMPLOYEE_CONTRIBUTION"));
        record.setVoluntaryContigent(rs.getBigDecimal("VOLUNTARY_CONTINGENT"));
        record.setVoluntaryRetirement(rs.getBigDecimal("VOLUNTARY_RETIREMENT"));
        record.setOtherInflows(rs.getBigDecimal("OTHER_INFLOWS"));
        record.setUnits(rs.getBigDecimal("NUMBER_OF_UNITS"));
        record.setFees(rs.getBigDecimal("FEES"));
        record.setWithdrawals(rs.getBigDecimal("OTHER_WITHDRAWALS"));
        record.setNet(rs.getBigDecimal("NET_CONTRIBUTIONS"));
        record.setBalance(BigDecimal.ZERO);
        String pfa = rs.getString("RELATED_PFA_CODE");
        String pin = rs.getString("rsapin");
        record.setPfa(pfa);
        record.setTotal(rs.getBigDecimal("TOTAL_CONTRIBUTIONS"));
        record.setId(pin+pfa+Integer.toString(i));
        return record;
    }
}
