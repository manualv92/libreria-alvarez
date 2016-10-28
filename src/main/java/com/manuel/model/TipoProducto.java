package com.manuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="tipo_producto")
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String descripcion;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "tipoProducto")
    @JsonIgnore
    private List<Producto> productos;

    public TipoProducto() {}

    public TipoProducto(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonIgnore
    public List<Producto> getProductos() {
        return productos;
    }

    @JsonIgnore
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
