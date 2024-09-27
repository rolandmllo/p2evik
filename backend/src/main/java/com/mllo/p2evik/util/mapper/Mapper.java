package com.mllo.p2evik.util.mapper;

import com.mllo.p2evik.dto.IDto;

/**
 * Mapper interface for mapping entities to DTOs and vice versa.
 * @param <E> Entity type
 * @param <D> DTO type
 */
public interface Mapper <E, D extends IDto> {
    D toDto(E entity);

    E toEntity(D dto);
}
