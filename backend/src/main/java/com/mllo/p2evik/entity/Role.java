package com.mllo.p2evik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mllo.p2evik.entity.types.UserRoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

/**
 * Entity representing a role.
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "roles")
public class Role {

    @Id
    @NotNull
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private UserRoleType roleType;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(UserRoleType name) {
        this.roleType = name;
    }

    @Override
    public String toString() {
        return this.roleType.name();
    }
}


