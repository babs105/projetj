package com.appavoc.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class VerificationToken {
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_VERIFIED = "VERIFIED";
    

    @Id
    private String id = UUID.randomUUID().toString();
    
    

    private String token;
    private String status;
    private LocalDateTime expiredDateTime;
    private LocalDateTime issuedDateTime;
    private LocalDateTime confirmedDateTime;

   
    private User user;

    public VerificationToken(){
        this.token = UUID.randomUUID().toString();
        this.issuedDateTime = LocalDateTime.now();
        this.expiredDateTime = this.issuedDateTime.plusDays(1);
        this.status = STATUS_PENDING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getExpiredDateTime() {
        return expiredDateTime;
    }

    public void setExpiredDateTime(LocalDateTime expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }

    public LocalDateTime getIssuedDateTime() {
        return issuedDateTime;
    }

    public void setIssuedDateTime(LocalDateTime issuedDateTime) {
        this.issuedDateTime = issuedDateTime;
    }

    public LocalDateTime getConfirmedDateTime() {
        return confirmedDateTime;
    }

    public void setConfirmedDateTime(LocalDateTime confirmedDateTime) {
        this.confirmedDateTime = confirmedDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	

}
