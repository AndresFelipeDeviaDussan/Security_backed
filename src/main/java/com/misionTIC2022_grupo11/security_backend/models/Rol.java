package com.misionTIC2022_grupo11.security_backend.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;
    private String name;
    private String description;


    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "rol")  // Update the FK in the PK
    @JsonIgnoreProperties("rol") //Within the system, many processes are related to JSON, so in order not to generate
    private List<User> users;    // conflicts when passing information, the command is in charge of ignoring the operation
                                //of said information (it only sends it).

    @ManyToMany
    @JoinTable(
            name = "permission_rols",
            joinColumns = @JoinColumn(name = "idRol"),
            inverseJoinColumns = @JoinColumn(name = "idPermission")
    )
    private Set<Permission> permissions;  // set = conjuntos

    public Integer getId() {
        return idRol;
    }

    public void setId(Integer id) {
        this.idRol = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
/**
 * All getter and setter
 * @return
 */

