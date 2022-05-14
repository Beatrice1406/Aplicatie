package org.loose.fis.sre.exceptions;

public class IncorectCredentials extends Exception {

    private String username;

    public IncorectCredentials(String username) {
        super(String.format("Incorrect credentials for username %s!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
