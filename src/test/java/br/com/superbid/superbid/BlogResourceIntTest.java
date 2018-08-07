package br.com.superbid.superbid;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.superbid.dto.BlogDTO;
import br.com.superbid.model.Blog;
import br.com.superbid.repository.BlogRepository;
import br.com.superbid.resource.BlogResource;
import br.com.superbid.service.BlogService;

@RunWith(SpringRunner.class)
public class BlogResourceIntTest {

	@InjectMocks
	private BlogResource blogResource;

	@Mock
	private BlogRepository blogRepository;

	@Mock	
	private BlogService blogService;

	@Mock
	private Blog blog;

	@Mock
	private BlogDTO blogDTO;


	private static final String URI_PUT = "/api/blog";
	private static final String URI_POST = "/api/blog";
	private static final String URI_DELETE = "/api/blog/{id}";
	private static final String URI_GET_ALL = "/api/blog/";
	private static final String URI_GET_BY_ID = "/api/palavras-chave/{id}";
	private static final Long ID = 1L;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		getByIdBlogDTO();
		createBlogDTO();
		updateBlogDTO();
		getAllBlogDTO();
		deleteBlogById();
	}

	private BlogDTO createBlogDTO(){
		blogDTO = new BlogDTO();
		blogDTO.setId(ID);
		blogDTO.setDataPublicacao(LocalDate.now());
		blogDTO.setDescricao("Descrição teste");
		blogDTO.setPost("Post teste");
		blogDTO.setTitulo("Titulo teste");
		return blogDTO;
	}	

	private BlogDTO updateBlogDTO(){
		blogDTO = new BlogDTO();
		blogDTO.setId(ID);
		blogDTO.setDataPublicacao(LocalDate.now());
		blogDTO.setDescricao("Descrição teste");
		blogDTO.setPost("Post teste");
		blogDTO.setTitulo("Titulo teste");
		return blogDTO;
	}

	private BlogDTO getAllBlogDTO(){
		blogDTO = new BlogDTO();
		blogDTO.setId(ID);
		blogDTO.setDataPublicacao(LocalDate.now());
		blogDTO.setDescricao("Descrição teste");
		blogDTO.setPost("Post teste");
		blogDTO.setTitulo("Titulo teste");
		return blogDTO;
	}

	private BlogDTO getByIdBlogDTO(){
		blogDTO = new BlogDTO();
		blogDTO.setId(1L);
		return blogDTO;
	}

	private BlogDTO deleteBlogById(){
		blogDTO = new BlogDTO();
		blogDTO.setId(1L);
		return blogDTO;
	}

	// Teste de verbo POST Blog
	@Test
	public void createBlogTest() throws URISyntaxException {
		ResponseEntity<BlogDTO> retorno = blogResource.createBlog(createBlogDTO());
		assertNotNull(retorno != null);
	}

	// Tester do verbo PUT de Blog
	@Test
	public void updateBlogTest() throws URISyntaxException {
		ResponseEntity<BlogDTO> retorno = blogResource.updateBlog(updateBlogDTO());
		assertNotEquals(null, blogDTO);
		assertNotNull(retorno != null);
	}

	// Teste do verbo DELETE de Blog
	@Test
	public void deleteBlogTest() throws URISyntaxException {
		blogResource.deleteBlogById(ID);
		ResponseEntity<Void> retorno = blogResource.deleteBlogById(ID);
		assertNotEquals(blogDTO, null);
		assertNotNull(retorno == null);
	}

	// Teste de verbo GET de Blog
	@Test
	public void getAllBlogTest() throws URISyntaxException {
		ResponseEntity<Page<BlogDTO>> retorno = blogResource.getAllBlog(getAllBlogDTO(), null);
		assertNotEquals(null, getAllBlogDTO());
		assertNotNull(retorno != null);
	}

	// Teste do verbo GET de Blog
	@Test
	public void getBlogByIdTest() throws URISyntaxException {
		BlogDTO retorno =  blogResource.getBlogById(ID);
		assertNotEquals(null, getByIdBlogDTO());
		assertNotNull(retorno != null);
	}
}