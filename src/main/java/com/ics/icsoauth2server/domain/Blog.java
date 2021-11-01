package com.ics.icsoauth2server.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "blog")
@Table(name = "blog")
public @Data class Blog extends BlogBaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true)
    private String UUID;

    @Column(name = "question",nullable = false)
    @NotEmpty(message = "Question must not be empty")
    private String question;

    @Lob
    @Column(name = "answer",nullable = false)
    @NotEmpty(message = "Answer must not be empty")
    private String answer;

}
