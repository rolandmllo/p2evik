package com.mllo.p2evik.service.impl;


import com.mllo.p2evik.dto.StudyGroupDto;
import com.mllo.p2evik.entity.StudyGroup;
import com.mllo.p2evik.repository.StudyGroupRepository;
import com.mllo.p2evik.service.StudyGroupService;
import com.mllo.p2evik.util.mapper.StudyGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing StudyGroup entities.
 */
@Service
@RequiredArgsConstructor
public class StudyGroupServiceImpl implements StudyGroupService<StudyGroupDto> {
    private final StudyGroupRepository studyGroupRepository;
    private final StudyGroupMapper studyGroupMapper;

    @Override
    public List<StudyGroupDto> getAll() {
        List<StudyGroup> studyGroups = studyGroupRepository.findAll();
        return studyGroups.stream()
                .map(studyGroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudyGroupDto getById(long id) {
        return studyGroupRepository.findById(id)
                .map(studyGroupMapper::toDto)
                .orElse(null);
    }
}
