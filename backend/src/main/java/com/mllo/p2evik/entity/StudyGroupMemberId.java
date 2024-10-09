package com.mllo.p2evik.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Composite key for StudyGroupMember entity.
 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StudyGroupMemberId implements Serializable {

    private long userId;

    private long studyGroupId;
}
