package br.com.superbid.resource;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.superbid.dto.BlogDTO;
import br.com.superbid.resource.util.HeaderUtil;
import br.com.superbid.service.BlogService;

@RestController
@RequestMapping("/api")
public class BlogResource {

	private final Logger log = LoggerFactory.getLogger(BlogResource.class);
	
	private final BlogService blogService;
	
	private static final String ENTITY_NAME = "Blog";
	
	private static final int QTD_REGISTRO = 10;
	
	public BlogResource(BlogService blogService) {
		this.blogService = blogService;
	}
	
	// API que realiza a criacao de um novo Blog
	@PostMapping("/blog")
	public ResponseEntity<BlogDTO> createBlog(@RequestBody BlogDTO blogDTO) throws URISyntaxException {
		log.debug("REST request to save Blog : {}", blogDTO);
		if (blogDTO.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new Blog cannot already have an ID")).body(null);
		}
		BlogDTO result = blogService.save(blogDTO);
        return ResponseEntity.created(new URI("/api/blog/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, null))
            .body(result);
	}

	// API que realiza a atualizacao de um blog ja existente ou cria um novo blog
    @PutMapping("/blog")
    public ResponseEntity<BlogDTO> updateBlog(@RequestBody BlogDTO blogDTO) throws URISyntaxException {
        log.debug("REST request to update Blog : {}", blogDTO);
        if (blogDTO.getId() == null) {
            return createBlog(blogDTO);
        }
        BlogDTO result = blogService.save(blogDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, blogDTO.getId().toString()))
            .body(result);
    }

    // API que retorno todos os blogs paginados
    @GetMapping("/blog-get-all")
    public ResponseEntity<Page<BlogDTO>> getAllBlog(BlogDTO blogDTO, @PageableDefault(size= QTD_REGISTRO) Pageable pageable) {
        log.debug("REST request to get all Blogs");
        return ResponseEntity.status(HttpStatus.OK).body(blogService.findAll(pageable));
     }
    
    // API que retorno os blogs por ID
    @GetMapping("/blog/{id}")
    public BlogDTO getBlogById(@PathVariable Long id) {
        log.debug("REST request to get Blogs : {}", id);
        return blogService.findOne(id);
    }
    
    // API que deleta os blogs por ID
    @DeleteMapping("/blog/{id}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable Long id) {
        log.debug("REST request to delete Blog : {}", id);
        blogService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}