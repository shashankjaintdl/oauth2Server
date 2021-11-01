package com.ics.icsoauth2server.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Entity(name = "category")
@Table(name = "category")
public @Data class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true)
    private String UUID;

    @Column(name = "category_name",unique = true)
    @NotEmpty(message = "Category must not be empty")
    private String categoryName;

    @Lob
    @Column(name = "description",length = 500)
    @NotEmpty(message = "Description must not be empty")
    @Max(message = "Length must be greater than 500 characters",value = 500)
    private String description;
}
