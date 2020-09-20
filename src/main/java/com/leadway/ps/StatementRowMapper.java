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
