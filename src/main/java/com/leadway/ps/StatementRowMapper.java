package com.leadway.ps;

import com.leadway.ps.model.StatementRequest;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Dev.io
 */
public class StatementRowMapper implements RowMapper<StatementRequest> {

    @Override
    public StatementRequest mapRow(ResultSet rs, int i) throws SQLException {
        StatementRequest req = new StatementRequest();
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
