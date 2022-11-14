package prac.jhkim.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prac.jhkim.graphql.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByIdx(int idx);
}
