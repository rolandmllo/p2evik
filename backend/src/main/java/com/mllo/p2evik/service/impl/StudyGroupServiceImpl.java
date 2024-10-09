package com.mllo.p2evik.service.impl;


import com.mllo.p2evik.dto.StudyGroupDto;
import com.mllo.p2evik.entity.StudyGroup;
import com.mllo.p2evik.entity.StudyGroupMember;
import com.mllo.p2evik.exception.ResourceNotFoundException;
import com.mllo.p2evik.repository.StudyGroupMemberRepository;
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
    private final StudyGroupMemberRepository studyGroupMemberRepository;
    private final StudyGroupMapper studyGroupMapper;

    @Override
    public StudyGroup findStudyGroupWithMembers(Long studyGroupId) throws ResourceNotFoundException {
        StudyGroup studyGroup = studyGroupRepository.findById(studyGroupId)
                .orElseThrow(() -> new ResourceNotFoundException("Study group not found"));
        List<StudyGroupMember> members = studyGroupMemberRepository.findByStudyGroupId(studyGroupId);
        studyGroup.setGroupMembers(members);
        return studyGroup;
    }

    @Override
    public StudyGroup AddStudyGroupWithMembers(StudyGroup studyGroup) {
        StudyGroup savedStudyGroup = studyGroupRepository.save(studyGroup);
        List<StudyGroupMember> members = studyGroup.getGroupMembers();
        members.forEach(member -> member.setStudyGroup(savedStudyGroup));
        studyGroupMemberRepository.saveAll(members);
        return savedStudyGroup;
    }

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
