package com.leadway.ps;

import com.leadway.ps.model.Statement;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Babatope Festus
 */
public class StatementRowMapper implements RowMapper<Statement> {

    @Override
    public Statement mapRow(ResultSet rs, int i) throws SQLException {
        Statement req = new Statement();
        req.setFundId(rs.getString("fundid"));
        req.setPrice(rs.getString("fundunitprice"));
        req.setBalance(rs.getBigDecimal("Balance"));
        req.setFirstName(rs.getString("firstname"));
        req.setMiddleName(rs.getString("MiddleName"));
        req.setCode(rs.getString("FundName"));
        req.setEmployer(rs.getString("employercode"));
        req.setPin(rs.getString("rsapin"));
        req.setSurname(rs.getString("lastname"));
        req.setStatus("PENDING");
        return req;
    }

}
