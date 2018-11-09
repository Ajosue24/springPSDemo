package com.proasecal.web.entity.administrar;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proasecal.web.entity.parametricas.Ciudad;
import com.proasecal.web.entity.parametricas.Departamentos;
import com.proasecal.web.entity.parametricas.Pais;
import com.proasecal.web.entity.parametricas.TipoDocumentoPais;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="LABORATORIOS_SEDES")
public class LaboratoriosSedes {


    @GenericGenerator(
            name = "laboratoriosSedesGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "id_laboratorios_sedes_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )

    @Id
    @Column(name = "id_laboratorios_sedes",columnDefinition = "serial")
    @GeneratedValue(generator = "laboratoriosSedesGenerator")
    private long idLaboratoriosSedes;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    @NotNull
    @JsonBackReference
    private Pais idPais;

    @ManyToOne
    @JoinColumn(name = "id_departamentos")
    @NotNull
    @JsonBackReference
    private Departamentos idDepartamentos;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    @NotNull
    @JsonBackReference
    private Ciudad idCiudad;

    @ManyToOne
    @JoinColumn(name = "id_clientes")
    @NotNull
    @JsonBackReference
    private Clientes clienteId;


    @ManyToOne
    @JoinColumn(name = "id_tipo_documento_pais")
    @NotNull
    @JsonBackReference
    private TipoDocumentoPais idTipoPais;

    @Column(name = "v_direccion")
    private String direccion;

    @Column(name = "v_correo")
    private String correo;

    @Column(name = "v_correo_alternativo")
    private String correoAlternativo;

    @Column(name="v_telefono")
    private String telefono;

    @Column(name="v_telefono_alternativo")
    private String telefonoAlternativo;

    @Column(name="b_imprimir_resultados")
    private Boolean imprimirResultados;

    @Column(name="d_fecha_creacion")
    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name="v_usuario_director")
    private String usuarioDirector;

    @Column(name="v_usuario_calidad")
    private String usuarioCalidad;

    @Column(name = "v_numero_identificacion",unique=true)
    private String numeroIdentificacion;

    @Column(name="v_razon_social")
    private String razonSocial;

    @Column(name="v_nombre_comercial")
    private String nombreComercial;

    @Column(name="b_estado")
    private Boolean estadoLabSedes;


    public long getIdLaboratoriosSedes() {
        return idLaboratoriosSedes;
    }

    public void setIdLaboratoriosSedes(long idLaboratoriosSedes) {
        this.idLaboratoriosSedes = idLaboratoriosSedes;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public Departamentos getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(Departamentos idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public Ciudad getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Ciudad idCiudad) {
        this.idCiudad = idCiudad;
    }

    public Clientes getClienteId() {
        return clienteId;
    }

    public void setClienteId(Clientes clienteId) {
        this.clienteId = clienteId;
    }

    public TipoDocumentoPais getIdTipoPais() {
        return idTipoPais;
    }

    public void setIdTipoPais(TipoDocumentoPais idTipoPais) {
        this.idTipoPais = idTipoPais;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCorreoAlternativo() {
        return correoAlternativo;
    }

    public void setCorreoAlternativo(String correoAlternativo) {
        this.correoAlternativo = correoAlternativo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoAlternativo() {
        return telefonoAlternativo;
    }

    public void setTelefonoAlternativo(String telefonoAlternativo) {
        this.telefonoAlternativo = telefonoAlternativo;
    }

    public Boolean getImprimirResultados() {
        return imprimirResultados;
    }

    public void setImprimirResultados(Boolean imprimirResultados) {
        this.imprimirResultados = imprimirResultados;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
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

    public Boolean getEstadoLabSedes() {
        return estadoLabSedes;
    }

    public void setEstadoLabSedes(Boolean estadoLabSedes) {
        this.estadoLabSedes = estadoLabSedes;
    }
}
