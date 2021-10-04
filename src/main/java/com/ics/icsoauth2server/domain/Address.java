package com.ics.icsoauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "addresses")
@Table(name = "addresses")
@Data @AllArgsConstructor @NoArgsConstructor
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String streetType1;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "address",fetch = FetchType.EAGER)
    private Set<UserAddress> userAddresses;

}
