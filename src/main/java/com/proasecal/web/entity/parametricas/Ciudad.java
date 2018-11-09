package com.proasecal.web.entity.parametricas;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proasecal.web.entity.administrar.Clientes;
import com.proasecal.web.entity.administrar.LaboratoriosSedes;
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
    @Column(name = "id_ciudad")
    @GeneratedValue(generator = "ciudadGenerator")
    long idCiudad;

    @Column(name = "v_descripcion")
    @NotNull
    String descripcionCiudad;

    @ManyToOne
    @JoinColumn(name = "id_departamentos")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonBackReference
    private Departamentos idDepartamentos;


    @OneToMany(mappedBy = "idCiudad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Clientes> clientesList;

    @OneToMany(mappedBy = "idCiudad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<LaboratoriosSedes> LaboratoriosSedesList;

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

    public Departamentos getIdDepartamento() {
        return idDepartamentos;
    }

    public void setIdDepartamento(Departamentos idDepartamento) {
        this.idDepartamentos = idDepartamentos;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }
}
