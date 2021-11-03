package com.donation.demo.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.donation.demo.model.User;
import com.donation.demo.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(Integer.toString(user.getUser_id()))
                .sign(Algorithm.HMAC256(user.getUser_password()));
        return token;
    }
}
