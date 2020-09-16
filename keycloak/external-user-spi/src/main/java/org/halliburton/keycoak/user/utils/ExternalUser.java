package org.halliburton.keycoak.user.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author André de Mattos Ferraz
 */
public class ExternalUser {

	@JsonProperty("id")
    public String id;
    @JsonProperty("username")
    public String username;
    @JsonProperty("email")
    public String email;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("realUsername")
    public String realUsername;
    @JsonProperty("password")
    public String password;

    public ExternalUser() {
    	
    }
    
    public ExternalUser(String id, String username, String firstName, String lastName, String realUsername, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = username;
        this.realUsername = realUsername;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRealUsername() {
        return realUsername;
    }

    public void setRealUsername(String realUsername) {
        this.realUsername = realUsername;
    }

    public String toString() {
        return "User[" + String.join(";", id, username, firstName, lastName, email) + "]";
    }
}
