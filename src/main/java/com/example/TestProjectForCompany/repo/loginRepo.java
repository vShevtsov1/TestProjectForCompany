package com.example.TestProjectForCompany.repo;

import com.example.TestProjectForCompany.loginDTO;
import org.springframework.http.ResponseEntity;

public interface loginRepo {
    ResponseEntity<String> login(loginDTO loginDTO);
}

