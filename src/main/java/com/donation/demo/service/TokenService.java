package com.donation.demo.service;

import com.donation.demo.model.User;

public interface TokenService {
    public String getToken(User user);
}
