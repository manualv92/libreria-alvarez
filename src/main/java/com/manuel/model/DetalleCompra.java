package com.manuel.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_compra")
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="compra_id")
    @JsonBackReference
    private Compra compra;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="producto_id")
    private Producto producto;

    private int cantidad;

    @Column(columnDefinition="Decimal(10,2)")
    private double precio;

    public DetalleCompra(){};

    public DetalleCompra(int cantidad, double precio) {
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
