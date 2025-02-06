package com.example.shiksha.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue
//    (strategy = GenerationType.IDENTITY)
    private Long id;


	@Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Column(nullable = false)
    private String emailid;

    @Column(nullable = false)
    private String password;

    // Getter and Setter methods
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
 		this.id = id;
 	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}