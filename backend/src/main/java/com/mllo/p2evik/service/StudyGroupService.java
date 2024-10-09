package com.mllo.p2evik.service;

import com.mllo.p2evik.dto.IDto;
import com.mllo.p2evik.entity.StudyGroup;
import com.mllo.p2evik.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service interface for managing StudyGroup entities.
 */
public interface StudyGroupService<T extends IDto> {

    /**
     * get all StudyGroup entities
     *
     * @return List of StudyGroup entities
     */
    List<T> getAll();

    /**
     * Get StudyGroup entity by id
     *
     * @param id of the StudyGroup entity
     * @return StudyGroup entity
     */
    T getById(long id);

    /**
     * Find StudyGroup entity by id with members
     *
     * @param studyGroup the study group entity
     * @return StudyGroup entity with members
     */
    StudyGroup AddStudyGroupWithMembers(StudyGroup studyGroup);

    /**
     * Find StudyGroup entity by id with members
     *
     * @param studyGroupId the id of the study group entity
     * @return StudyGroup entity with members
     * @throws ResourceNotFoundException if the study group is not found
     */
    StudyGroup findStudyGroupWithMembers(Long studyGroupId) throws ResourceNotFoundException;


}
