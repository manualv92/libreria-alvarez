package com.manuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descripcion;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    @JsonIgnore
    private List<Cliente> clientes;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    @JsonIgnore
    private List<Proveedor> proveedores;

    public TipoDocumento(){}

    public TipoDocumento(int id,String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonIgnore
    public List<Cliente> getClientes() {
        return clientes;
    }

    @JsonIgnore
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @JsonIgnore
    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    @JsonIgnore
    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
