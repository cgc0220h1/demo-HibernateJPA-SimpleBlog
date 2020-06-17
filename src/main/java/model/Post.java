package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private Timestamp createTime;

    @Column
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    @Access(AccessType.PROPERTY)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @Access(AccessType.PROPERTY)
    private Author author;

    @OneToMany(fetch = FetchType.EAGER)
    @Access(AccessType.PROPERTY)
    private Collection<Comment> comments;
}
