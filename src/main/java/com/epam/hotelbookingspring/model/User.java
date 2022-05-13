package com.epam.hotelbookingspring.model;


import com.epam.hotelbookingspring.model.security.Role;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name = "is_non_locked")
    private boolean accountNonLocked;
    @Column(name = "is_account_non_expired")
    private boolean accountNonExpired;
    @Column(name = "is_credentials_non_expired")
    private boolean credentialsNonExpired;
    @Column(name = "is_enabled")
    private boolean isAccountEnabled;

}
