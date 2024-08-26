package com.mllo.p2evik.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {

    private @Id
    @NotNull
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
