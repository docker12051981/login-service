package com.annuaire.loginservice.service;

import com.annuaire.loginservice.dto.CredentialsDto;
import com.annuaire.loginservice.dto.UserDto;
import com.annuaire.loginservice.exception.AppException;
import com.annuaire.loginservice.mappers.UserMapper;
import com.annuaire.loginservice.model.AccountUser;
import com.annuaire.loginservice.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }



    public UserDto signIn(CredentialsDto credentialsDto) {
        logger.info("Methode signIn(): before : CredentialsDto : {} ", credentialsDto);
        var user = userRepository.findByIdentifiant(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Utilisateur n'existe pas", HttpStatus.NOT_FOUND));
        logger.info("Methode signIn(): after check login : user : {} ", user);

            return userMapper.toUserDto(user, createToken(user));

       // throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        Optional<AccountUser> userOptional = userRepository.findByIdentifiant(login);

        if (userOptional.isEmpty()) {
            throw new AppException("User n'est pas trouvé", HttpStatus.NOT_FOUND);
        }
        else {
            logger.info("user is here");
        }

        AccountUser user = userOptional.get();
        return userMapper.toUserDto(user, createToken(user));
    }

    private String createToken(AccountUser user) {
        Claims claims = Jwts.claims().setSubject(user.getIdentifiant());
        claims.put("role",user.getRole());
        claims.put("organisme",user.getOrganismeId());
        Date now = new Date();
        Date validity = new Date(now.getTime() + 8600000); // +2 hour

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
