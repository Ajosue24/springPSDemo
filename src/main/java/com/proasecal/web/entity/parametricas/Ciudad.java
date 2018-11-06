package com.proasecal.web.entity.parametricas;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proasecal.web.entity.administrar.Clientes;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "CIUDAD")
public class Ciudad {

    @GenericGenerator(
            name = "ciudadGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ciudad_id_ciudad_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )

    @Id
    @Column(name = "ID_CIUDAD")
    @GeneratedValue(generator = "ciudadGenerator")
    long idCiudad;

    @Column(name = "V_DESCRIPCION")
    @NotNull
    String descripcionCiudad;

    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    private Departamento idDepartamento;


    @OneToMany(mappedBy = "idCiudad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Clientes> clientesList;

    public long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(long idCiudad) {
        this.idCiudad = idCiudad;
    }


    public String getDescripcionCiudad() {
        return descripcionCiudad;
    }

    public void setDescripcionCiudad(String descripcionCiudad) {
        this.descripcionCiudad = descripcionCiudad;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }
}
