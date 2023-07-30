package com.niklas.test.data.model;

/**
 * Datenklasse, die Benutzerinformationen für angemeldete Benutzer erfasst, die aus
 * dem LoginRepository abgerufen werden
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}