package com.mllo.p2evik.entity;

import com.mllo.p2evik.entity.types.MembershipType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a study group member.
 */
@Getter
@Setter
@Entity(name = "study_group_members")
public class StudyGroupMember {

    @EmbeddedId
    private StudyGroupMemberId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("studyGroupId")
    @JoinColumn(name = "study_group_id")
    private StudyGroup studyGroup;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

}
