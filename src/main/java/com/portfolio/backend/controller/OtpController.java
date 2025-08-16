package com.portfolio.backend.controller;

import com.portfolio.backend.service.EmailService;
import com.portfolio.backend.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
public class OtpController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private  OtpService otpService;

    @CrossOrigin(origins = "https://iamhritik.vercel.app")
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {

        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);

        return ResponseEntity.ok("OTP sent to email: " + email);
    }

}

