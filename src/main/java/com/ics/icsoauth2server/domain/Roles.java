package com.ics.icsoauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;


@Entity(name = "roles")
@Table(name = "roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class Roles {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(unique = true,nullable = false)
    private String uuid;

    @Column(name = "role",unique = true,nullable = false)
    private String role;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = EAGER,cascade = ALL)
    @JoinTable(
            name = "permission_role",
            joinColumns = {
                    @JoinColumn(name = "permission_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")
            }
    )
    private Set<Permissions> permissions;

    public Roles(String uuid,String role, String description){
        this.uuid = uuid;
        this.role = role;
        this.description = description;
    }

    public Roles(String uuid,String role, String description,Set<Permissions> permissions){
        this(uuid,role,description);
        if(permissions.isEmpty() || permissions == null)
            throw new IllegalArgumentException("Permissions must not be null");
        this.permissions = permissions;
    }



}
