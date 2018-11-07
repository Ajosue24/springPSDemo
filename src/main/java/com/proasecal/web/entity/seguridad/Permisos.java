package com.proasecal.web.entity.seguridad;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PERMISOS")
public class Permisos {

    public Permisos(Long idPermisos){
        this.idPermisos = idPermisos;
    }


    public Permisos(String descripcion,
                    String nombrePermiso,
                    String url,
                    Modulos idModulos,
                    Permisos permisos,
                    Set<Roles> listRoles){
        this.idPermisos = idPermisos;
        this.descripcion = descripcion;
        this.nombrePermiso = nombrePermiso;
        this.url = url;
        this.idModulos = idModulos;
        this.listRoles = listRoles;
        this.permisos = permisos;

    }


    public Permisos() {
    }

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

//DEPENDENCIAS
    @ManyToOne
    @JoinColumn(name = "id_modulos")
    private Modulos idModulos;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="ROLES_PERMISOS", joinColumns=@JoinColumn(name="id_permisos"), inverseJoinColumns=@JoinColumn(name="id_roles"))
    private Set<Roles> listRoles;

    @ManyToOne
    @JoinColumn(name = "id_permisos_dependiente")
    private Permisos permisos;

    @OneToMany(mappedBy = "idPermisos", fetch = FetchType.EAGER)
    private List<Permisos> permisosList;

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

    public Permisos getPermisos() {
        return permisos;
    }

    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }

    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

}
