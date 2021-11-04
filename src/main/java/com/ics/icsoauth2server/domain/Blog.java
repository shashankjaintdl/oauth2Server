package com.ics.icsoauth2server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;

@Entity(name = "blog")
@Table(name = "blog")
@NoArgsConstructor
public @Data class Blog extends BlogBaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true,updatable = false)
    @NotNull(message = UUID_NOT_NULL)
    @NotEmpty(message = UUID_NOT_EMPTY)
    @NotBlank(message = UUID_NOT_BLANK)
    private String UUID;

    @Column(name = "title",nullable = false,updatable = false)
    @NotEmpty(message = BLOG_TOPIC_NOT_EMPTY)
    @NotNull(message = BLOG_TOPIC_NOT_NULL)
    @NotBlank(message = BLOG_TOPIC_NOT_BLANK)
    private String title;

    @Lob
    @Column(name = "contents",nullable = false)
    @NotEmpty(message = BLOG_CONTENT_NOT_EMPTY)
    @NotNull(message = BLOG_CONTENT_NOT_NULL)
    private String contents;

    @Column(name = "tags")
    @ElementCollection(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private Set<String> tags;

    @ManyToOne
    @JsonIgnore
    @NotNull(message = "User information must not be null")
    private User user;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public Blog( String UUID,String title, String contents,String username){
        this.UUID = UUID;
        this.title = title;
        this.contents = contents;
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
        this.setCreatedBy(username);
    }

    public Blog( String UUID, String title, String contents,String username, Set<String> tags,User user) {
        this(UUID,title,contents,username);
        if(tags.isEmpty() || tags==null)
            throw new IllegalArgumentException("Tags must be provided");
        this.tags = tags;
        if(user==null)
            throw new IllegalArgumentException("User is not allowed");
        this.user = user;
    }

}
