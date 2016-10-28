package com.manuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    @Column(columnDefinition="Decimal(10,2)")
    private double precio;
    @Column(columnDefinition="Decimal(10,2)")
    private double precioVenta;
    private int stock;
    private int habilitado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto tipoProducto;
    @ManyToMany(mappedBy="productos")
    private List<Proveedor> proveedores;
    private String marca;

    public Producto() {}

    public Producto(long id, String nombre, double precio, double precioVenta, int stock, int habilitado, TipoProducto tipoProducto, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.habilitado = habilitado;
        this.tipoProducto = tipoProducto;
        this.marca = marca;
        this.precioVenta = precioVenta;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getMarca() { return marca; }

    public void setMarca(String marca) { this.marca = marca; }

    public double getPrecioVenta() {return precioVenta;}

    public void setPrecioVenta(double precioVenta) {this.precioVenta = precioVenta;}
}
