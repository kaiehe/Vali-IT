package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount(String accountNr, String name, double balance) {
        String sql = "INSERT INTO account(account_number, client, balance) VALUES(:dbAccNo, :dbName, :dbAmount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        paramMap.put("dbName", name);
        paramMap.put("dbAmount", balance);
        jdbcTemplate.update(sql, paramMap);
    }

    public Double getBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE account_number =:dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paraMap, Double.class);
        return balance;
    }

    public Boolean accountStatus(String accountNr) {
        String status = "SELECT blocked FROM account WHERE account_number = :dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        return jdbcTemplate.queryForObject(status, paraMap, Boolean.class);
    }

    public void updateBalance(String accountNr, Double deposit) {
        String updateSql = "UPDATE account SET balance =:dbBalance WHERE account_number=:dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbBalance", deposit);
        paraMap.put("dbAccountNo", accountNr);
        jdbcTemplate.update(updateSql, paraMap);
    }

    public void lock(String accountNr) {
        Map<String, Object> paramMap = new HashMap<>();
        String setBlock = "UPDATE account SET blocked= true WHERE account_number =:dbAccountNo";
        paramMap.put("dbAccountNo", accountNr);
        jdbcTemplate.update(setBlock, paramMap);
    }

    public void unlock(String accountNr) {
        Map<String, Object> paramMap = new HashMap<>();
        String setUnblok = "UPDATE account SET blocked= false WHERE account_number =:dbAccountNo";
        paramMap.put("dbAccountNo", accountNr);
        jdbcTemplate.update(setUnblok, paramMap);
    }

    //salvestab transaction historysse kõik raha lisamised
    public void transactionHistory(LocalDateTime date, String accountNr, Double deposit, Double updatedBalance) {
        Map<String, Object> paramMap = new HashMap<>();
        String history = "INSERT INTO transaction_history(date, account_number, deposit, balance) VALUES(:dbDate, :dbAccount_number, :dbdeposit, :dbBalance)";
        paramMap.put("dbDate", date);
        paramMap.put("dbAccount_number", accountNr);
        paramMap.put("dbdeposit", deposit);
        paramMap.put("dbBalance", updatedBalance);
        jdbcTemplate.update(history, paramMap);
    }

    public void transactionHistoryWithdraw(LocalDateTime date, String accountNr, Double withdraw, Double updatedBalance) {
        Map<String, Object> paramMap = new HashMap<>();
        String historyWithdraw = "INSERT INTO transaction_history(date, account_number, withdraw, balance) VALUES(:dbDate, :dbAccount_number, :dbWithdraw, :dbBalance)";
        paramMap.put("dbDate", date);
        paramMap.put("dbAccount_number", accountNr);
        paramMap.put("dbWithdraw", withdraw);
        paramMap.put("dbBalance", updatedBalance);
        jdbcTemplate.update(historyWithdraw, paramMap);
    }
}
