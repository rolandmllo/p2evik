package com.mllo.p2evik.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "users")
public class User {

    private @Id
    @NotNull
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    @Size(min = 7, max = 20)
    @Column(unique = true, length = 60)
    String email;

    @NotNull
    @Size(min = 7, max = 30)
    String password;


    @NotNull
    @Size(min = 1, max = 30)
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_roles", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
    private Set<Role> roles;

    public User(String name) {
        this.name = name;
        this.roles = Set.of(new Role("USER", this));
    }
}
