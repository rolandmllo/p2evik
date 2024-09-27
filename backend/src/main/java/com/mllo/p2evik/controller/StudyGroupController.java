package com.mllo.p2evik.controller;

import com.mllo.p2evik.dto.StudyGroupDto;
import com.mllo.p2evik.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/api/study-group")
public class StudyGroupController {
    private final StudyGroupService<StudyGroupDto> studyGroupService;

    @GetMapping("/study-group")
    public List<StudyGroupDto> getAll() {
        return studyGroupService.getAll();
    }
}
