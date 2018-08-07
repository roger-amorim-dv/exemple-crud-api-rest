package br.com.superbid.mapper;

import org.mapstruct.Mapper;

import br.com.superbid.dto.BlogDTO;
import br.com.superbid.model.Blog;

@Mapper(componentModel = "spring", uses = {})
public interface BlogMapper extends EntityMapper<BlogDTO, Blog> {

	default Blog fromId(Long id) {
        if (id == null) {
            return null;
        }
        Blog blog = new Blog();
        blog.setId(id);
        return blog;
    }
}
