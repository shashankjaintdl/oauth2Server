package com.ics.icsoauth2server.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pages_endpoint")
public @Data class PageEndpoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true,updatable = false,nullable = false)
    @NotEmpty(message = "UUID must not be empty")
    private String UUID;

    @Column(name = "end_point",unique = true,updatable = false,nullable = false)
    @NotEmpty(message = "Endpoint must not be empty!")
    private String endPoint;

    @ManyToOne
    private PageGroup pageGroup;

    @ManyToOne
    private User user;

    @Column(name = "is_disabled")
    private Boolean isDisabled;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "temp_disabled")
    private Boolean isTempdisbled;

}
