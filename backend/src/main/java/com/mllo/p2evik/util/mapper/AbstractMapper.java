package com.mllo.p2evik.util.mapper;

import com.mllo.p2evik.dto.IDto;

/**
 * Abstract class for mapper.
 * @param <E> Entity type
 * @param <D> DTO type
 */
public abstract class AbstractMapper<E, D extends IDto> implements Mapper<E, D> {

}
