package com.mllo.p2evik.repository;

import com.mllo.p2evik.entity.StudyGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing StudyGroupMember entities.
 */
public interface StudyGroupMemberRepository extends JpaRepository<StudyGroupMember, Long> {

    /**
     * Finds a StudyGroupMember by their user ID.
     *
     * @param studyGroupId the user ID of the StudyGroupMember to find
     * @return the StudyGroupMember object with the given user ID, or null if not found
     */
    List<StudyGroupMember> findByStudyGroupId(long studyGroupId);
}
