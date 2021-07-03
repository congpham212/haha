package com.evotek.nagakawa_be.payload;

public class LoginRequest {
    private String email;
    private String password;


    public LoginRequest() {
        super();
    }

    public LoginRequest(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
