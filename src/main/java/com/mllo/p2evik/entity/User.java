package com.mllo.p2evik.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    private @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    private String name;

    @Column(unique = true, length = 60)
    String email;

    String password;


    @ManyToMany( fetch = FetchType.EAGER ,cascade = { CascadeType.ALL })
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
    private Set<Role> roles;

    public User(String name) {
        this.name = name;
        this.roles = Set.of(new Role("USER"));
    }
}
