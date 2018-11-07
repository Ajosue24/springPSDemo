package com.proasecal.web.entity.seguridad;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USUARIOS_SISTEMA")
public class Usuarios {

    @GenericGenerator(
            name = "usuarioGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "usuario_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            })

    @Id
    @Column(name = "id_usuarios_sistema", columnDefinition = "serial")
    @GeneratedValue(generator = "usuarioGenerator")
    private long idUsuario;

    @Column(name = "v_usuario",unique=true)
    @NotNull
    private String nombreUsuario;

    @Column(name = "v_password")
    @NotNull
    private String password;

    @Column(name = "v_nombre")
    private String nombres;

    @Column(name = "v_apellido")
    private String apellidos;

    @Column(name = "b_estado")
    @NotNull
    private Boolean estado = true;

    @Column(name="d_fecha_creacion")
    @CreationTimestamp
    private Date fechaCreacion = new Date();

    @Column(name="n_cod_proasecal")
    private Integer codProasecal;

    @Column(name="b_password_reset")
    private Boolean passwordReset;
    @Column(name = "v_correo")
    private String correo;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="ROLES_X_USUARIOS", joinColumns=@JoinColumn(name="id_usuarios_sistema"), inverseJoinColumns=@JoinColumn(name="id_roles"))
    private List<Roles> rolesList;


    public Usuarios() {}

    public Usuarios(Usuarios usuario) {
        this.nombreUsuario = usuario.getNombreUsuario();
        this.password = usuario.getPassword();
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.estado  = usuario.getEstado();
        this.fechaCreacion = usuario.getFechaCreacion();
        this.codProasecal = usuario.getCodProasecal();
        this.correo = usuario.getCorreo();
        this.rolesList = usuario.getRolesList();
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCodProasecal() {
        return codProasecal;
    }

    public void setCodProasecal(Integer codProasecal) {
        this.codProasecal = codProasecal;
    }

    public Boolean getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(Boolean passwordReset) {
        this.passwordReset = passwordReset;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

}
