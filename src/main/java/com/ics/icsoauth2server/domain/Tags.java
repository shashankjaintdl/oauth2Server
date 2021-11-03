package com.ics.icsoauth2server.domain;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity(name = "tags")
@Table(name = "tags")
public @Data class Tags extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true,updatable = false)
    private String UUID;

    @Column(name = "tag_name",unique = true,nullable = false)
    @NotEmpty(message = "Category must not be empty")
    private String tagName;

    @Lob
    @Column(name = "description",length = 500)
    @NotEmpty(message = "Description must not be empty")
    @Max(message = "Length must be greater than 500 characters",value = 500)
    private String description;

    @ManyToOne
    private Blog blog;
}
