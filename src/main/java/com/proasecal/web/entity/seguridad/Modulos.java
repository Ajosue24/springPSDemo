package com.proasecal.web.entity.seguridad;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MODULOS")
public class Modulos {

    @GenericGenerator(
            name = "modulosGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "modulos_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            })

    @Id
    @Column(name = "id_modulos", columnDefinition = "serial")
    @GeneratedValue(generator = "modulosGenerator")
    private long idModulos;

    @Column(name = "v_nombre")
    @NotNull
    private String nombreModulo;

    @Column(name = "v_descripcion")
    private String descripcion;


    //uno a muchos con la tabla permisos
    @OneToMany(mappedBy = "idModulos", fetch = FetchType.EAGER)
    private List<Permisos> permisosList;


    @ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="MODULOS_X_ROLES", joinColumns=@JoinColumn(name="id_modulos"), inverseJoinColumns=@JoinColumn(name="id_roles"))
    private Set<Roles> rolesList;



    public long getIdModulos() {
        return idModulos;
    }

    public void setIdModulos(long idModulos) {
        this.idModulos = idModulos;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Permisos> getPermisosList() {
        return permisosList;
    }

    public void setPermisosList(List<Permisos> permisosList) {
        this.permisosList = permisosList;
    }

    public Set<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(Set<Roles> rolesList) {
        this.rolesList = rolesList;
    }
}
