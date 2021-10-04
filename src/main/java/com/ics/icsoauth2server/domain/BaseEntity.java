package com.ics.icsoauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Data @AllArgsConstructor @NoArgsConstructor
public class BaseEntity {

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
}
