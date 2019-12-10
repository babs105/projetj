package com.appavoc.viewmodel;

import com.appavoc.model.User;

public class LoginUser {
    private User user;
    private String sessionCookie;

    public LoginUser(User user, String sessionCookie) {
        this.user = user;
        this.sessionCookie = sessionCookie;
    }

    public LoginUser() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionCookie() {
        return sessionCookie;
    }

    public void setSessionCookie(String sessionCookie) {
        this.sessionCookie = sessionCookie;
    }
}
