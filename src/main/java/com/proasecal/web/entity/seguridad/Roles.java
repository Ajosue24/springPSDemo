package com.proasecal.web.entity.seguridad;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ROLES")
public class Roles {
    @GenericGenerator(
            name = "rolesGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "roles_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            })

    @Id
    @Column(name = "id_roles", columnDefinition = "serial")
    @GeneratedValue(generator = "rolesGenerator")
    private long idRoles;


    @Column(name="v_nombre_rol",unique=true)
    @NotNull
    private String nombreRol;

    @Column(name="v_descripcion")
    private String descripcion;

    @Column(name="d_fecha_creacion")
    @CreationTimestamp
    private Date fechaCreacion = new Date();

    @Column(name = "b_estado")
    private Boolean estado = true;

    @ManyToMany(mappedBy = "rolesList")
    private List<Usuarios> usuariosList = new ArrayList<>();

    @ManyToMany(mappedBy = "listRoles", fetch=FetchType.EAGER)
    private List<Permisos> permisosList = new ArrayList<>();

    @ManyToMany(mappedBy = "rolesList",fetch=FetchType.EAGER)
    private List<Modulos> modulosList = new ArrayList<>();

    public long getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(long idRoles) {
        this.idRoles = idRoles;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public List<Modulos> getModulosList() {
        return modulosList;
    }

    public void setModulosList(List<Modulos> modulosList) {
        this.modulosList = modulosList;
    }
}
