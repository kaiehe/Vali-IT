package ee.bcs.valiit.MySecurity;

import ee.bcs.valiit.myServiceAndRepository.NewUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("api")
@RestController
public class LoginController {

    @Autowired
    private AuthService authService;



    @PostMapping("/public/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @CrossOrigin
    @PostMapping("public/newuser")
    public void newUser(@RequestBody NewUser newUser) {
        authService.newUser(newUser);
    }




}
