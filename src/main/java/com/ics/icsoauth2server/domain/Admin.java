package com.ics.icsoauth2server.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;

public class Admin extends AbstractPersistable<Long> {

    @Column(name = "uuid")
    private String UUID;


}
