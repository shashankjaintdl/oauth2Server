package com.ics.icsoauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "address_type")
@Table(name = "address_type")
@Data @AllArgsConstructor @NoArgsConstructor
public class AddressType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true,updatable = false)
    private String UUID;

    @Column(name = "address_type",length = 30)
    private String addressType;

    @Column(name = "address_description")
    private String addressDescription;

    @OneToMany(mappedBy = "addressType",fetch = FetchType.EAGER)
    Set<UserAddress> userAddress;
}

