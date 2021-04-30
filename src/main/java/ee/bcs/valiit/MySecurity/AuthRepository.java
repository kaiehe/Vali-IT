package ee.bcs.valiit.MySecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String checkPassword (String username) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT password FROM users WHERE username=:dbUsername";
        paramMap.put("dbUsername", username);
        String password = jdbcTemplate.queryForObject(sql, paramMap, String.class);
        return password;
    }
    public void newUser(String username, String password, String client)  {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "INSERT INTO users(username, password, client) VALUES(:dbusername, :dbpassword, :dbclient)";
        paramMap.put("dbusername", username);
        paramMap.put("dbpassword", password);
        paramMap.put("dbclient", client);
        jdbcTemplate.update(sql, paramMap);
    }
}
