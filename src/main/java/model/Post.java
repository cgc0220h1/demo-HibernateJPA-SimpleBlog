package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
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
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    public Post() {
    }

    public Post(String title, String content, Timestamp createTime, String imageLink, Tag tag, Author author) {
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.imageLink = imageLink;
        this.tag = tag;
        this.author = author;
    }

    public Post(Long id, String title, String content, Timestamp createTime, String imageLink, Tag tag, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.imageLink = imageLink;
        this.tag = tag;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", imageLink='" + imageLink + '\'' +
                ", tag=" + tag +
                ", author=" + author +
                '}';
    }
}
