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
    private String apellido;
    private long telefono;
    private String email;
    private String direccion;
    private int habilitado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_documento")
    private TipoDocumento tipoDocumento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_persona")
    private TipoPersona tipoPersona;
    private long nroDocumento;
    private long nroCuit;

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

    public Proveedor(long id, String nombre, String apellido, long telefono, String email, String direccion, int habilitado, List<Producto> productos, TipoDocumento tipoDocumento, TipoPersona tipoPersona, long nroDocumento, long nroCuit) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.habilitado = habilitado;
        this.productos = productos;
        this.tipoDocumento = tipoDocumento;
        this.tipoPersona = tipoPersona;
        this.nroDocumento = nroDocumento;
        this.nroCuit = nroCuit;
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

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

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

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

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

    public TipoDocumento getTipoDocumento() {return tipoDocumento;}

    public void setTipoDocumento(TipoDocumento tipoDocumento) {this.tipoDocumento = tipoDocumento;}

    public TipoPersona getTipoPersona() {return tipoPersona;}

    public void setTipoPersona(TipoPersona tipoPersona) {this.tipoPersona = tipoPersona;}

    public long getNroDocumento() {return nroDocumento;}

    public void setNroDocumento(long nroDocumento) {this.nroDocumento = nroDocumento;}

    public long getNroCuit() {return nroCuit;}

    public void setNroCuit(long nroCuit) {this.nroCuit = nroCuit;}

    @JsonIgnore
    public List<Compra> getCompras() { return compras; }

    @JsonIgnore
    public void setCompras(List<Compra> compras) { this.compras = compras; }
}
