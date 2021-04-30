package ee.bcs.valiit.MySecurity;

import ee.bcs.valiit.myExceptions.MyApplicationException;
import ee.bcs.valiit.myServiceAndRepository.NewUser;
import ee.bcs.valiit.solution.exception.SampleApplicationException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginRequest loginRequest) {
//        String password = authRepository.checkPassword(loginRequest.getUsername());
//        if (loginRequest.getPassword().equals(password)) {
        if (passwordEncoder.matches(loginRequest.getPassword(), authRepository.checkPassword(loginRequest.getUsername()))) {
            Date today = new Date();
            Date tokenExpirationDate = new Date(today.getTime() + 1000 * 60 * 60 * 24);
            JwtBuilder jwtBuilder = Jwts.builder()
                    .setExpiration(tokenExpirationDate)
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "c2VjcmV0")
                    .claim("username", loginRequest.getUsername());
            return jwtBuilder.compact();
        } else {
            throw new MyApplicationException("Vale kasutajanimi või parool");
        }
    }

    public void newUser(NewUser newUser) {
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        authRepository.newUser(newUser.getUsername(), encodedPassword, newUser.getClient());
    }
}
