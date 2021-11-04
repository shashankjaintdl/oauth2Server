package com.ics.icsoauth2server.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public @Data class BlogBaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "created_date",
            length = 19,
            updatable = false
    )
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            name = "updated_date",
            length = 19
    )
    private Date updatedDate;

    @Column(name = "createdBy")
    private String createdBy;

}
