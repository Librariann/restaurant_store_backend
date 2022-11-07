package com.restaurant.store.User.service;

import com.restaurant.store.User.domain.User;
import com.restaurant.store.User.dto.FindUser;
import com.restaurant.store.User.dto.UserJoin;
import com.restaurant.store.User.repository.UserRepository;
import com.restaurant.store.common.ResponseMessage;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void createAccount(UserJoin userJoin) {
        User saveUser = userJoin.toEntity();
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
        Optional<User> user = userRepository.findById(id);
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
