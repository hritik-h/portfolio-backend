package com.portfolio.backend.controller;

import com.portfolio.backend.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final OtpService otpService;

    @Autowired
    public ResumeController(OtpService otpService) {
        this.otpService = otpService;
    }


    @CrossOrigin(origins = "https://iamhritik.vercel.app")
    @GetMapping("/download")
    public ResponseEntity<?> downloadResume(@RequestParam String email, @RequestParam String otp) {
        if (otpService.verifyOtp(email, otp)) {
            try {
                // Load file from classpath inside JAR
                ClassPathResource resource = new ClassPathResource("static/Hritik_Resume.pdf");
                InputStream inputStream = resource.getInputStream();
                byte[] resumeBytes = inputStream.readAllBytes();

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Hrithik_Resume.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(resumeBytes);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }
}

