package com.proasecal.web.entity.parametricas;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proasecal.web.entity.administrar.Clientes;
import com.proasecal.web.entity.administrar.LaboratoriosSedes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "DEPARTAMENTOS")
public class Departamentos {

    @GenericGenerator(
            name = "departamentoGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "id_departamento_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )



    @Id
    @Column(name = "id_departamentos")
    @GeneratedValue(generator = "departamentoGenerator")
    private long idDepartamentos;


    @Column(name = "v_nombre")
    @NotNull
    private String nombreDepartamento;


    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais idPais;


    @OneToMany(mappedBy = "idDepartamentos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Ciudad> ciudadList;

    @OneToMany(mappedBy = "idDepartamentos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Clientes> listaClientes;

    @OneToMany(mappedBy = "idDepartamentos", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<LaboratoriosSedes> LaboratoriosSedesList;

    public long getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(long idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Pais getIdPais() {
        return idPais;
    }

    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public List<Ciudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(List<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public List<Clientes> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Clientes> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<LaboratoriosSedes> getLaboratoriosSedesList() {
        return LaboratoriosSedesList;
    }

    public void setLaboratoriosSedesList(List<LaboratoriosSedes> laboratoriosSedesList) {
        LaboratoriosSedesList = laboratoriosSedesList;
    }
}