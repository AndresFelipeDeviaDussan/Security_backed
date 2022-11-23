package com.misionTIC2022_grupo11.security_backend.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermission;
    @Column(name = "url",nullable = false,unique = true)
    private String url;
    @Column(name = "method",nullable = false)
    private String method;

    @ManyToMany(mappedBy = "permissions")
    private Set<Rol> rols; // set = conjuntos

    public Integer getId() {
        return idPermission;
    }

    public void setId(Integer id) {
        this.idPermission = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<Rol> getRols() {
        return rols;
    }

    public void setRols(Set<Rol> rols) {
        this.rols = rols;
    }
}
/**
 * All getter and setter
 * @return
 */
