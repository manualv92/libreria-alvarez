package com.manuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private long telefono;
    private String email;
    private int habilitado;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "proveedor")
    @JsonIgnore
    private List<Compra> compras;

    @ManyToMany
    @JoinTable(
            name="producto_proveedor",
            joinColumns=@JoinColumn(name="proveedor_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="producto_id", referencedColumnName="id"))
    private List<Producto> productos;

    public Proveedor(){}

    public Proveedor(long id, String nombre, long telefono, String email, int habilitado, List<Producto> productos) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.habilitado = habilitado;
        this.productos = productos;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @JsonIgnore
    public List<Compra> getCompras() { return compras; }

    @JsonIgnore
    public void setCompras(List<Compra> compras) { this.compras = compras; }
}
