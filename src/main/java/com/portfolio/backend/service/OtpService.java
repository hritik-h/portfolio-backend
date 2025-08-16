package com.portfolio.backend.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {
    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    public String generateOtp(String email){
        String otp = String.valueOf((int)(Math.random() * 9000) + 1000);
        otpStorage.put(email, otp);
        return otp;
    }

    public boolean verifyOtp(String email,String otp) {
        if (otp.equals(otpStorage.get(email))) {
            return true;
        } else {
            return false;
        }
    }
}
