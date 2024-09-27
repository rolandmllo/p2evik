package com.mllo.p2evik.dto;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public record StudyGroupDto(long id, String name)
        implements IDto {
}
