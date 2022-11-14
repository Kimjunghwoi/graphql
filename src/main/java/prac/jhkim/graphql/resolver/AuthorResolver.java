package prac.jhkim.graphql.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.jhkim.graphql.domain.Author;
import prac.jhkim.graphql.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthor(int idx) {
        return authorRepository.findByIdx(idx);

    }

    @Transactional
    public Author createAuthor(String name, String thumbnail) {

        Author author = Author.builder()
                .name(name)
                .thumbnail(thumbnail)
                .build();
        author = authorRepository.save(author);

        return author;

    }

    @Transactional
    public Author updateAuthorName(int idx, String name) {
        Author author = authorRepository.findByIdx(idx);
        author.setName(name);
        return author;
    }

}
