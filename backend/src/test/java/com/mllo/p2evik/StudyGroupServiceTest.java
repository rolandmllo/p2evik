package com.mllo.p2evik;

import com.mllo.p2evik.dto.StudyGroupDto;
import com.mllo.p2evik.entity.StudyGroup;
import com.mllo.p2evik.repository.StudyGroupRepository;
import com.mllo.p2evik.service.impl.StudyGroupServiceImpl;
import com.mllo.p2evik.util.mapper.StudyGroupMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class StudyGroupServiceImplTest {

    @Mock
    private StudyGroupRepository studyGroupRepository;

    @Mock
    private StudyGroupMapper studyGroupMapper;

    @InjectMocks
    private StudyGroupServiceImpl studyGroupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_returnsAllStudyGroups() {
        StudyGroup studyGroup1 = new StudyGroup().setId(1L).setGroupName("Group 1");
        StudyGroup studyGroup2 = new StudyGroup().setId(2L).setGroupName("Group 2");
        StudyGroupDto studyGroupDto1 = new StudyGroupDto(1L, "Group 1");
        StudyGroupDto studyGroupDto2 = new StudyGroupDto(2L, "Group 2");

        when(studyGroupRepository.findAll()).thenReturn(Arrays.asList(studyGroup1, studyGroup2));
        when(studyGroupMapper.toDto(studyGroup1)).thenReturn(studyGroupDto1);
        when(studyGroupMapper.toDto(studyGroup2)).thenReturn(studyGroupDto2);

        List<StudyGroupDto> result = studyGroupService.getAll();

        assertEquals(2, result.size());
        assertEquals(studyGroupDto1, result.get(0));
        assertEquals(studyGroupDto2, result.get(1));
    }

    @Test
    void getById_returnsStudyGroup_whenIdExists() {
        StudyGroup studyGroup = new StudyGroup().setId(1L).setGroupName("Group 1");
        StudyGroupDto studyGroupDto = new StudyGroupDto(1L, "Group 1");

        when(studyGroupRepository.findById(1L)).thenReturn(Optional.of(studyGroup));
        when(studyGroupMapper.toDto(studyGroup)).thenReturn(studyGroupDto);

        StudyGroupDto result = studyGroupService.getById(1L);

        assertEquals(studyGroupDto, result);
    }

    @Test
    void getById_returnsNull_whenIdDoesNotExist() {
        when(studyGroupRepository.findById(1L)).thenReturn(Optional.empty());

        StudyGroupDto result = studyGroupService.getById(1L);

        assertNull(result);
    }
}