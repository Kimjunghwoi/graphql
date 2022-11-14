package prac.jhkim.graphql.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prac.jhkim.graphql.domain.Author;
import prac.jhkim.graphql.domain.Post;
import prac.jhkim.graphql.repository.AuthorRepository;
import prac.jhkim.graphql.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

/*
    RESTful API에서는 Controller -> Service -> Repository 구조를 통해 데이터를 호출하고 받아왔다면,
    GraphQL에서는 .graphqls -> Resolver -> Repository 구조를 통해 데이터를 받아온다고 볼 수 있는데요.

    Resolve는 클라이언트가 요청하는 GraphQL을 처리하여 필요한 데이터를 반환해주는 비즈니스 로직으로,
    GraphQL의 쿼리를 통해 호출되는 실제 행동이 정의되는 곳입니다.

    Spring에서는 GraphQLQueryResolver, GraphQLMutationResolver 인터페이스를 제공합니다.
*/

    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost(int idx) {
        return postRepository.findByIdx(idx);

    }

    @Transactional
    public Post createPost(String title, String content, String category, int authorIdx) {
        Author findAuthor = authorRepository.findByIdx(authorIdx);
        Post post = Post.builder()
                .title(title)
                .contents(content)
                .category(category)
                .author(findAuthor)
                .build();
        post = postRepository.save(post);
        return post;
    }
    @Transactional
    public Post updatePostTitle(int idx, String title) {
        Post post = postRepository.findByIdx(idx);
        post.setTitle(title);
        return post;
    }

}
