package ee.bcs.valiit.myServiceAndRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Repository
public class BankRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createAccount(String accountNr, String name, double balance) {
        String sql = "INSERT INTO account(accountno, name, balance) VALUES(:dbAccNo, :dbName, :dbAmount)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dbAccNo", accountNr);
        paramMap.put("dbName", name);
        paramMap.put("dbAmount", balance);
        jdbcTemplate.update(sql, paramMap);
    }

    public Double getBalance(String accountNr) {
        String sql = "SELECT balance FROM account WHERE accountno =:dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        Double balance = jdbcTemplate.queryForObject(sql, paraMap, Double.class);
        return balance;
    }

    public Boolean accountStatus(String accountNr) {
        String status = "SELECT blocked FROM account WHERE accountno = :dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbAccountNo", accountNr);
        return jdbcTemplate.queryForObject(status, paraMap, Boolean.class);
    }

    public void updateBalance(String accountNr, Double deposit) {
        String updateSql = "UPDATE account SET balance =:dbBalance WHERE accountno=:dbAccountNo";
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("dbBalance", deposit);
        paraMap.put("dbAccountNo", accountNr);
        jdbcTemplate.update(updateSql, paraMap);
    }

    public void lock(String accountNr) {
        Map<String, Object> paramMap = new HashMap<>();
        String setBlock = "UPDATE account SET blocked= true WHERE accountno =:dbAccountNo";
        paramMap.put("dbAccountNo", accountNr);
        jdbcTemplate.update(setBlock, paramMap);
    }

    public void unlock(String accountNr) {
        Map<String, Object> paramMap = new HashMap<>();
        String setUnblok = "UPDATE account SET blocked= false WHERE accountno =:dbAccountNo";
        paramMap.put("dbAccountNo", accountNr);
        jdbcTemplate.update(setUnblok, paramMap);
    }
}
