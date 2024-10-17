package edu.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username; // Cambiado de email a username

    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() { // Cambiado a Long
        return id;
    }
}
