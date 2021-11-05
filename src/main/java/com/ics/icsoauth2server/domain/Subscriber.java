package com.ics.icsoauth2server.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "subscribers")
@Table(name = "subscribers")
public @Data  class Subscriber extends BaseEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "UUID",unique = true,updatable = false)
    private String UUID;

    @Column(name = "email_id",unique = true)
    private String emailId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "message",length = 1000)
    private String message;

    public Subscriber(){}

    public Subscriber(String UUID,String emailId,String firstName,String lastName,String message){
        this.UUID = UUID;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.setCreatedDate(new Date());
        this.setUpdatedDate(new Date());
    }
}
