package com.eventz.io.eventz.config.security.security;

import com.eventz.io.eventz.config.security.models.JwtUser;
import com.eventz.io.eventz.models.response.BaseResponse;
import com.eventz.io.eventz.models.response.ResponseCodes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Michael.Akobundu on 4/2/2019.
 */
@Component
public class JwtGenerator {

    @Value("${Eventz.signing.key}")
    private String signingKey;

    public BaseResponse generate(JwtUser jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId", String.valueOf(jwtUser.getId()));
        claims.put("role", jwtUser.getRole());


        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();
        BaseResponse response = new BaseResponse();
        response.setResponseCode(ResponseCodes.SUCCESS.getCode());
        response.setResponseMessage(token);
        return response;
    }
}
