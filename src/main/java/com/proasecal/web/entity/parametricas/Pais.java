package com.proasecal.web.entity.parametricas;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proasecal.web.entity.administrar.Clientes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "PAIS")
public class Pais {

    @GenericGenerator(
            name = "paisGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "id_pais_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )

    @Id
    @Column(name = "ID_PAIS")
    @GeneratedValue(generator = "paisGenerator")
    private long idPais;

    @Column(name = "V_NOMBRE")
    @NotNull
    private String nombrePais;

    @Column(name = "B_ESTADO")
    private Boolean estado;


    @OneToMany(mappedBy = "idPais", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Departamentos> departamentoList;

    @OneToMany(mappedBy = "idPais", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Clientes> clientesList;

    @OneToMany(mappedBy = "idPais", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<TipoDocumentoPais> tipoDocumentoPaisList;

    public long getIdPais() {
        return idPais;
    }

    public void setIdPais(long idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Departamentos> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamentos> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public List<TipoDocumentoPais> getTipoDocumentoPaisList() {
        return tipoDocumentoPaisList;
    }

    public void setTipoDocumentoPaisList(List<TipoDocumentoPais> tipoDocumentoPaisList) {
        this.tipoDocumentoPaisList = tipoDocumentoPaisList;
    }
}
