package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

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

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    @Access(AccessType.PROPERTY)
    private Collection<Post> posts;
}
