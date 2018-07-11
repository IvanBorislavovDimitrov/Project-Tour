package app.model.dtos;

import org.hibernate.validator.constraints.Length;

public class UserDto {

    @Length(min = 3)
    private String username;

    @Length(min = 3)
    private String email;

    @Length(min = 3)
    private String phoneNumber;

    @Length(min =  3)
    private String password;

    private String roles;

    public UserDto() {
    }

    public UserDto(@Length(min = 3) String username, @Length(min = 3) String email,
                   @Length(min = 3) String phoneNumber, @Length(min = 3) String password, String roles) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
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

    public String getRoles() {
        return this.roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
