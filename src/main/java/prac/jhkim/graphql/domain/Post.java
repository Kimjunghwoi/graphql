package prac.jhkim.graphql.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String title;

    private String contents;

    private String category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_idx", referencedColumnName = "idx")
    private Author author;


}
