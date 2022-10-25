package com.example.TestProjectForCompany.controlers;

import com.example.TestProjectForCompany.loginDTO;
import com.example.TestProjectForCompany.repo.loginRepo;
import com.example.TestProjectForCompany.services.TokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/login")
public class loginController implements loginRepo {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${password}")
    private String password;
    @Autowired
    private TokenServices tokenServices;
    @Override
    @GetMapping()
    public ResponseEntity<String> login(@RequestBody loginDTO loginDTO) {
        if(loginDTO.getLogin().equals("user"))
        {
            if(this.password.equals(loginDTO.getPassword())){
                return ResponseEntity.ok(tokenServices.generateTokenUser());

            }
            else {
                return ResponseEntity.badRequest().build();
            }
        }
        else {
            return ResponseEntity.badRequest().build();
        }

    }
}
