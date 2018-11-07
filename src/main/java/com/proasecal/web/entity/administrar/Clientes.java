package com.proasecal.web.entity.administrar;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proasecal.web.entity.parametricas.Ciudad;
import com.proasecal.web.entity.parametricas.Departamento;
import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.entity.parametricas.TipoDocumentoPais;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="CLIENTES")
public class Clientes {
    @GenericGenerator(
            name = "clienteGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "cliente_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )


    @Id
    @Column(name = "ID_CLIENTES",columnDefinition = "serial")
    @GeneratedValue(generator = "clienteGenerator")
    long clienteId;

    @ManyToOne
    @JoinColumn(name = "ID_PAIS")
    @NotNull
    @JsonBackReference
    private Pais idPais;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_DOCUMENTO_PAIS")
    @NotNull
    @JsonBackReference
    private TipoDocumentoPais idTipoPais;

    @Column(name = "V_USUARIO_DIRECTOR")
    private String usuarioDirector;

    @Column(name = "V_USUARIO_CALIDAD")
    private String usuarioCalidad;

    @Column(name = "V_ID_CLIENTE")
    @NotNull
    String numeroIdentificacionCliente;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO")
    @NotNull
    @JsonBackReference
    private Departamento idDepartamento;

    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD")
    @NotNull
    @JsonBackReference
    private Ciudad idCiudad;

    @Column(name = "V_DIRECCION_CLIENTE")
    String direccionCliente;
    @Column(name = "V_CORREO_CLIENTE")
    String correoCliente;
    @Column(name = "V_CORREO_ALTERNATIVO")
    String correoAlternativo;

    @Column(name = "V_TELEFONO_CLIENTE")
    String telefonoCliente;
    @Column(name = "V_TELEFONO_ALTERNATIVO")
    String telefonoAlternativo;

    @Column(name = "V_RAZON_SOCIAL")
    @NotNull
    String razonSocial;
    @Column(name = "V_NOMBRE_COMERCIAL")
    String nombreComercial;

    @Column(name = "B_ESTADO", columnDefinition = "DEFAULT true")
    Boolean estadoCliente = true;

    @Column(name = "D_FECHA_CREACION")
    Date fechaCreacion;

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public TipoDocumentoPais getIdTipoPais() {
        return idTipoPais;
    }

    public void setIdTipoPais(TipoDocumentoPais idTipoPais) {
        this.idTipoPais = idTipoPais;
    }

    public String getUsuarioDirector() {
        return usuarioDirector;
    }

    public void setUsuarioDirector(String usuarioDirector) {
        this.usuarioDirector = usuarioDirector;
    }

    public String getUsuarioCalidad() {
        return usuarioCalidad;
    }

    public void setUsuarioCalidad(String usuarioCalidad) {
        this.usuarioCalidad = usuarioCalidad;
    }

    public String getNumeroIdentificacionCliente() {
        return numeroIdentificacionCliente;
    }

    public void setNumeroIdentificacionCliente(String numeroIdentificacionCliente) {
        this.numeroIdentificacionCliente = numeroIdentificacionCliente;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getCorreoAlternativo() {
        return correoAlternativo;
    }

    public void setCorreoAlternativo(String correoAlternativo) {
        this.correoAlternativo = correoAlternativo;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getTelefonoAlternativo() {
        return telefonoAlternativo;
    }

    public void setTelefonoAlternativo(String telefonoAlternativo) {
        this.telefonoAlternativo = telefonoAlternativo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public Boolean getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(Boolean estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}