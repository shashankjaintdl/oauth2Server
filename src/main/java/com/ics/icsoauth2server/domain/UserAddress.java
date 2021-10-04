package com.ics.icsoauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "user_address")
@Table(name = "user_address")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserAddress {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    // user relation
    @Column(name = "uuid")
    private String uuid;

    @ManyToOne
    private User user;

    // Address type relation
    @ManyToOne
    private AddressType addressType;

    // Address Relation
    @ManyToOne
    private Address address;

}
