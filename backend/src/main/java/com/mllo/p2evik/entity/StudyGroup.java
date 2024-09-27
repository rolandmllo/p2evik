package com.mllo.p2evik.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Entity representing a study group.
 */
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity(name = "study_groups")
public class StudyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_group_id")
    private long id;

    @NotNull
    private String groupName;

    private String description;

    @NotNull
    @ManyToMany
    @JoinTable(name = "study_group_owner", joinColumns = {
            @JoinColumn(name = "study_group_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")})
    private List<User> owner;

    @OneToMany(mappedBy = "studyGroup")
    private List<StudyGroupMember> groupMembers;

    @OneToMany(mappedBy = "studyGroup")
    private List<Lecture> lectures;

}
