package model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "post")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private Timestamp createTime;

    @Column
    private String imageLink;

    @Column
    private String tag;

    public Blog() {
    }

    public Blog(Long id, String author, String title, String content, Timestamp createTime, String imageLink, String tag) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.imageLink = imageLink;
        this.tag = tag;
    }

    public Blog(String author, String title, String content, Timestamp createTime, String imageLink, String tag) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.imageLink = imageLink;
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String content) {
        this.title = content;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", imageLink='" + imageLink + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
