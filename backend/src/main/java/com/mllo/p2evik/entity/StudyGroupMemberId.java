package com.mllo.p2evik.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Composite key for StudyGroupMember entity.
 */
@Embeddable
public class StudyGroupMemberId implements Serializable {

    private long userId;

    private long studyGroupId;
}
