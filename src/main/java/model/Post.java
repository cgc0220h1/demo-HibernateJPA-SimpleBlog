package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

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
    @Access(AccessType.PROPERTY)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    @Access(AccessType.PROPERTY)
    private Author author;

    @OneToMany
    @Access(AccessType.PROPERTY)
    private Collection<Comment> comment;

    public Post() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Collection<Comment> getComment() {
        return comment;
    }

    public void setComment(Collection<Comment> comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", imageLink='" + imageLink + '\'' +
                ", tag=" + category +
                ", author=" + author +
                ", comment=" + comment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return Objects.equals(getId(), post.getId()) &&
                Objects.equals(getTitle(), post.getTitle()) &&
                Objects.equals(getContent(), post.getContent()) &&
                Objects.equals(getCreateTime(), post.getCreateTime()) &&
                Objects.equals(getImageLink(), post.getImageLink()) &&
                Objects.equals(getCategory(), post.getCategory()) &&
                Objects.equals(getAuthor(), post.getAuthor()) &&
                Objects.equals(getComment(), post.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getContent(), getCreateTime(), getImageLink(), getCategory(), getAuthor(), getComment());
    }
}
