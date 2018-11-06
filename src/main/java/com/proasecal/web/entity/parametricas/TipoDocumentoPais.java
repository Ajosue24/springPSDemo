package com.proasecal.web.entity.parametricas;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proasecal.web.entity.administrar.Clientes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
    @Table(name = "TIPO_DOCUMENTO_PAIS")
    public class TipoDocumentoPais {

        @GenericGenerator(
                name = "idTipoDocumentoPaisGenerator",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "idTipoDocumentoPaisGenerator_id_tipo_documento_pais_seq"),
                        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
                }
        )

        @Id
        @Column(name = "ID_TIPO_DOCUMENTO_PAIS")
        @GeneratedValue(generator = "idTipoDocumentoPaisGenerator")
        long idTipoPais;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    @NotNull
    @JsonBackReference
    private Pais idPais;

    @Column(name="v_descripcion")
    String descripcionId;

    @OneToMany(mappedBy = "idTipoPais", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Clientes> clientesList;


    public long getIdTipoPais() {
        return idTipoPais;
    }

    public void setIdTipoPais(long idTipoPais) {
        this.idTipoPais = idTipoPais;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public String getDescripcionId() {
        return descripcionId;
    }

    public void setDescripcionId(String descripcionId) {
        this.descripcionId = descripcionId;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }
}

