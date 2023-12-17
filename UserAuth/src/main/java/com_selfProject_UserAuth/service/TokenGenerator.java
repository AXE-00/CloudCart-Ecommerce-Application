package com_selfProject_UserAuth.service;

import com_selfProject_UserAuth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenGenerator implements ITokenGenerator {

    @Override
    public Map<String, String> storeToken(User user) {
        Map<String,Object> userData = new HashMap<>();
        userData.put("email", user.getUserEmail());
        userData.put("role", user.getRole());

        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(userData)
                .signWith(SignatureAlgorithm.HS512,"secKey1945")
                .compact();
        Map<String,String> genToken = new HashMap<>();
        genToken.put("Token",token);
        genToken.put("email",user.getUserEmail());
        genToken.put("role", user.getRole());
        return genToken;
    }
}
