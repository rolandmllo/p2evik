package com.mllo.p2evik.util.mapper;

import com.mllo.p2evik.dto.StudyGroupDto;
import com.mllo.p2evik.entity.StudyGroup;
import org.springframework.stereotype.Component;

/**
 * Mapper for StudyGroup entities.
 */
@Component
public class StudyGroupMapper extends AbstractMapper<StudyGroup, StudyGroupDto> {
    @Override
    public StudyGroupDto toDto(StudyGroup entity) {
        return new StudyGroupDto(entity.getId(), entity.getGroupName());
    }

    @Override
    public StudyGroup toEntity(StudyGroupDto dto) {
        StudyGroup studyGroup =  new StudyGroup();
        studyGroup.setId(dto.id());
        studyGroup.setGroupName(dto.name());
        return studyGroup;
    }
}
