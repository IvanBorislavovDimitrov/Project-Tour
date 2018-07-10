package app.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Privilege.class)
    private Set<Privilege> privileges;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    public User() {
        this.privileges = new HashSet<>();
    }

    public User(String name, String email, String phoneNumber, String password) {
        this.username = name;
        this.email = email;
        this.phone = phoneNumber;
        this.password = password;
        this.privileges = new HashSet<>();
        this.reservations = new HashSet<>();
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Privilege> getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(Set<Privilege> roles) {
        this.privileges = roles;
    }

    public Set<Reservation> getReservations() {
        return this.reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
