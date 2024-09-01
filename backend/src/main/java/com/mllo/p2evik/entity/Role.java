package com.mllo.p2evik.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "roless")
public class Role {

    @Id
    @NotNull
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(String name, User user) {
        this.name = name;
    }
}


