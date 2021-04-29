package ee.bcs.valiit.MySecurity;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LoginController {

    @PostMapping("/api/public/login")
    public String bankLogin(@RequestBody LoginRequest loginRequest) {
        Date today = new Date();
        Date tokenExpirationDate = new Date(today.getTime()+1000*60*60*24);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setExpiration(tokenExpirationDate)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                .claim("username", loginRequest.getUsername());
        return jwtBuilder.compact();
    }
}
