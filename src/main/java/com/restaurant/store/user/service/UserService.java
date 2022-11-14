package com.restaurant.store.user.service;

import com.restaurant.store.user.domain.User;
import com.restaurant.store.user.dto.UserJoin;
import com.restaurant.store.user.repository.UserRepository;
import com.restaurant.store.common.ResponseMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.restaurant.store.common.Constant.SECRET_KEY;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void createAccount(UserJoin userJoin) {
        System.out.println(userJoin);
        User saveUser = userJoin.toEntity();
        String encodePassword = passwordEncoder.encode(saveUser.getPassword());
        saveUser.setPassword(encodePassword);
        userRepository.save(saveUser);
    }

    public String makeJwtToken() {
        Date now = new Date();

        return Jwts.builder()
                .addClaims(
                        Map.of("name", "Librarian", "price", 3000)
                )
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
                .setIssuer("fresh") // (2)
                .setIssuedAt(now) // (3)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
                .claim("id", "아이디") // (5)
                .claim("email", "ajufresh@gmail.com")
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // (6)
                .compact();
    }

    public Claims parseJwtToken(String authorizationHeader) {

        return Jwts.parser()
                .setSigningKey(SECRET_KEY) // (3)
                .parseClaimsJws(authorizationHeader) // (4)
                .getBody();
    }
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

    public String deleteUser(Long id) {
        Optional<User> user = findUser(id);
        if(user.isEmpty()){
            return ResponseMessage.DELETE_FAIL;
        }
        userRepository.deleteById(id);
        return ResponseMessage.DELETE_SUCCESS;
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
