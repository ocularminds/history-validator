package com.leadway.ps;

import com.leadway.ps.model.StatementRequest;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Babatope Festus
 */
public class StatementRowMapper implements RowMapper<StatementRequest> {

    @Override
    public StatementRequest mapRow(ResultSet rs, int i) throws SQLException {
        StatementRequest req = new StatementRequest();        
        req.setPrice(rs.getBigDecimal("fundunitprice"));
        req.setFirstName(rs.getString("firstname"));
        req.setMiddleName(rs.getString("MiddleName"));
        req.setFundId(rs.getInt("fundid"));
        req.setCode(rs.getString("FundName"));
        req.setEmployer(rs.getString("employercode"));
        req.setPin(rs.getString("rsapin"));
        req.setSurname(rs.getString("lastname"));
        req.setStatus("PENDING");
        return req;
    }

}
