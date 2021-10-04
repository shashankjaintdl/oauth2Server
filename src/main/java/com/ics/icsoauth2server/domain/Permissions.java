package com.ics.icsoauth2server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "permission")
@Table(
        name = "permissions",
        uniqueConstraints = {
        @UniqueConstraint(name = "uk_permission",columnNames = "permission")}
)
@Data @NoArgsConstructor
public class Permissions{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column(name = "permission",unique = true)
    private String permission;

    private String description;

    public Permissions(String uuid,String permission,String description){
        this.uuid = uuid;
        this.permission = permission;
        this.description = description;
    }



}
