package app.model.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserDto {

    @Length(min = 3)
    private String username;

    @Length(min = 3)
    private String email;

    @Length(min = 3)
    private String phoneNumber;

    @Length(min =  3)
    private String password;

    public UserDto() {
    }

    public UserDto(@NotNull String username, @NotNull String email, @NotNull String phoneNumber) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
