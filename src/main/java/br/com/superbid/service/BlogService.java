package br.com.superbid.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.superbid.dto.BlogDTO;

public interface BlogService {

	BlogDTO save(BlogDTO blogDTO);

	Page<BlogDTO> findAll(Pageable pageable);

	BlogDTO findOne(Long id);

	void delete(Long id);
}

