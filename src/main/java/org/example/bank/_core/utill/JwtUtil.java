package org.example.bank._core.utill;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.bank.user.SessionUser;
import org.example.bank.user.User;

import java.sql.Date;

public class JwtUtil {
    public static String create(User user){
        String jwt = JWT.create()
                .withSubject("blog")
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000*60*60))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .sign(Algorithm.HMAC512("metacoding"));
        return jwt;
    }

    public static SessionUser verify(String jwt){
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512("metacoding")).build().verify(jwt);
        int id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();

        return SessionUser.builder()
                .id(id)
                .username(username)
                .build();
    }
}