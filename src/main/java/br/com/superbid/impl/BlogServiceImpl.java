package br.com.superbid.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.superbid.dto.BlogDTO;
import br.com.superbid.mapper.BlogMapper;
import br.com.superbid.model.Blog;
import br.com.superbid.repository.BlogRepository;
import br.com.superbid.service.BlogService;

@Transactional
@Service
public class BlogServiceImpl implements BlogService{

	private final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);
	
	private final BlogRepository blogRepository;
	
	private final BlogMapper blogMapper;
	
	public BlogServiceImpl(BlogRepository blogRepository, BlogMapper blogMapper) {
		this.blogMapper = blogMapper;
		this.blogRepository = blogRepository;
	}
	
	@Override
	public BlogDTO save(BlogDTO blogDTO) {
		log.debug("Request to save Blog : {}", blogDTO);
		Blog blog = blogMapper.toEntity(blogDTO);
        blog = blogRepository.save(blog);
        return blogMapper.toDto(blog);
	}

	@Override
	public Page<BlogDTO> findAll(Pageable pageable) {
		log.debug("Request to get all Blog");
		return blogRepository.findAll(pageable).map(blogMapper::toDto);
	}

	@Override
	public BlogDTO findOne(Long id) {
	       log.debug("Request to get Sla : {}", id);
	        Blog blog = blogRepository.getOne(id);
	        return blogMapper.toDto(blog);
	}

	@Override
	public void delete(Long id) {	
		log.debug("Request to delete Blog : {}", id);
		 blogRepository.deleteById(id);
	}
}