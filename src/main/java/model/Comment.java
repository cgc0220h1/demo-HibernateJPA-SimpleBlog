package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String commentAuthorName;

    @Column
    private String commentAuthorEmail;

    @Column
    @CreationTimestamp
    private Timestamp createTime;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @Access(AccessType.PROPERTY)
    private Post post;
}
