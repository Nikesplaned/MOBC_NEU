package com.niklas.test.data;

import com.niklas.test.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Klasse, die die Authentifizierung mit Anmeldeinformationen übernimmt und Benutzerinformationen abruft.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}