package com.restaurant.store.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static com.restaurant.store.common.Constant.SECRET_KEY;

public class JwtController {
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
}
