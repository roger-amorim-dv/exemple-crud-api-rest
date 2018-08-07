package br.com.superbid.mapper;

import java.util.List;

import br.com.superbid.dto.BlogDTO;

public interface EntityMapper<D, E> {

	E toEntity(BlogDTO blogDTO);

	D toDto(E entity);

	List <E> toEntity(List<D> dtoList);

	List <D> toDto(List<E> entityList);
}
