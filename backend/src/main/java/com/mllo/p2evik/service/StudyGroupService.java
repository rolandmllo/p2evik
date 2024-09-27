package com.mllo.p2evik.service;

import com.mllo.p2evik.dto.IDto;

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

}
