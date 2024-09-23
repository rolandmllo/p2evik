package com.mllo.p2evik.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entity representing a lecture.
 */
@Entity
@Setter
@Getter
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    private LocalDateTime date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;
}
