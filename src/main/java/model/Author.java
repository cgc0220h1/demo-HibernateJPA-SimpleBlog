package model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Date dateOfBirth;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column
    private String imageLink;

    @OneToMany
    @Access(AccessType.PROPERTY)
    private Collection<Post> post;

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Collection<Post> getPost() {
        return post;
    }

    public void setPost(Collection<Post> posts) {
        this.post = posts;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", posts=" + post +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) &&
                Objects.equals(getName(), author.getName()) &&
                Objects.equals(getDateOfBirth(), author.getDateOfBirth()) &&
                Objects.equals(getPhoneNumber(), author.getPhoneNumber()) &&
                Objects.equals(getEmail(), author.getEmail()) &&
                Objects.equals(getBio(), author.getBio()) &&
                Objects.equals(getImageLink(), author.getImageLink()) &&
                Objects.equals(getPost(), author.getPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDateOfBirth(), getPhoneNumber(), getEmail(), getBio(), getImageLink(), getPost());
    }
}
