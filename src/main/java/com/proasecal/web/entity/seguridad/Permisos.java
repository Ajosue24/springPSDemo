package com.proasecal.web.entity.seguridad;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "PERMISOS")
public class Permisos {


    @GenericGenerator(
            name = "permisosGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "permisos_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            })

    @Id
    @Column(name = "id_permisos", columnDefinition = "serial")
    @GeneratedValue(generator = "permisosGenerator")
    private long idPermisos;

    @Column(name="v_nombre")
    @NotNull
    private String nombrePermiso;

    @Column(name="v_descripcion")
    private String descripcion;

    @Column(name="v_url")
    @NotNull
    private String url;


    @ManyToOne
    @JoinColumn(name = "id_modulos")
    private Modulos idModulos;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="ROLES_PERMISOS", joinColumns=@JoinColumn(name="id_permisos"), inverseJoinColumns=@JoinColumn(name="id_roles"))
    private Set<Roles> listRoles;

    public long getIdPermisos() {
        return idPermisos;
    }

    public void setIdPermisos(long idPermisos) {
        this.idPermisos = idPermisos;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Modulos getIdModulos() {
        return idModulos;
    }

    public void setIdModulos(Modulos idModulos) {
        this.idModulos = idModulos;
    }

    public Set<Roles> getListRoles() {
        return listRoles;
    }

    public void setListRoles(Set<Roles> listRoles) {
        this.listRoles = listRoles;
    }
}
