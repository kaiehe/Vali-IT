package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryRowMapper implements RowMapper<HistoryList> {
    @Override
    public HistoryList mapRow(ResultSet resultSet, int i) throws SQLException {
        HistoryList historyResult = new HistoryList();
        historyResult.setDate(resultSet.getTimestamp("date"));
        historyResult.setAccountNumber(resultSet.getString("account_number"));
        historyResult.setDeposit(resultSet.getDouble("deposit"));
        historyResult.setWithdraw(resultSet.getDouble("withdraw"));
        historyResult.setBalance(resultSet.getDouble("balance"));
        return historyResult;
    }
}
