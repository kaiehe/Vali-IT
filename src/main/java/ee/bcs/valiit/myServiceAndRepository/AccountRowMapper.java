package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<AccountList> {
    @Override
    public AccountList mapRow(ResultSet resultSet, int i) throws SQLException {
        AccountList result = new AccountList();
        result.setAccountNumber(resultSet.getString("account_number"));
        result.setClient(resultSet.getString("client"));
        result.setBalance(resultSet.getDouble("balance"));
        result.setBlocked(resultSet.getBoolean("blocked"));

        return result;
    }
}
