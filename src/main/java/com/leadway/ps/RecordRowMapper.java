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
        record.setDateReceived(rs.getDate("Valuedate"));
        record.setMonthStart(rs.getDate("StartPeriod"));
        record.setMonthEnd(rs.getDate("EndPeriod"));
        record.setType(rs.getString("ContEmploerID"));
        record.setEmployer(rs.getBigDecimal("ERAmount"));
        record.setContribution(rs.getBigDecimal("EEAmount"));
        record.setVoluntaryContigent(rs.getBigDecimal("VoluntaryAmount"));
        record.setVoluntaryRetirement(BigDecimal.ZERO);
        record.setOtherInflows(BigDecimal.ZERO);
        record.setUnits(rs.getBigDecimal("TotalUnits"));
        record.setFees(rs.getBigDecimal("AdminFee"));
        record.setWithdrawals(rs.getBigDecimal("Payments"));
        record.setNet(rs.getBigDecimal("NetAmount"));
        record.setPfa("023");
        return record;
    }
}
