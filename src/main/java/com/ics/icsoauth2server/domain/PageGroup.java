package com.ics.icsoauth2server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "page_group")
public @Data class PageGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(name = "uuid",unique = true,updatable = false,nullable = false)
    @NotEmpty(message = "UUID must not be empty")
    private String UUID;

    @Column(name = "group_name",unique = true,columnDefinition = "TEXT")
    @NotEmpty(message = "Page group name must not be empty")
    private String groupName;

    @OneToMany(mappedBy = "pageGroup",cascade = CascadeType.ALL)
    private Set<PageEndpoint> pageEndpoints;

}
