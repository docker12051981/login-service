package com.annuaire.loginservice.controller;

import com.annuaire.loginservice.dto.CredentialsDto;
import com.annuaire.loginservice.dto.UserDto;
import com.annuaire.loginservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserService userService;
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserDto> signIn(@RequestBody CredentialsDto credentialsDto) {
        log.info("Trying to login {}", credentialsDto.getLogin());
        return ResponseEntity.ok(userService.signIn(credentialsDto));

    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<UserDto> signup(@RequestBody CredentialsDto credentialsDto) {
        log.info("Trying to register {}", credentialsDto.getLogin());
        return ResponseEntity.ok(userService.signIn(credentialsDto));

    }
    @CrossOrigin(origins = "*")
    @PostMapping("/validateToken")
    public ResponseEntity<UserDto> signIn(@RequestParam String token) {
        log.info("Trying to validate token {}", token);
        return ResponseEntity.ok(userService.validateToken(token));
    }
}
