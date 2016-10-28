package com.manuel.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition="Decimal(10,2)")
    private double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    private String fecha;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private Set<DetalleCompra> detalleCompras;


    public Compra(){};

    public Compra(long id, double total, Proveedor proveedor, String fecha) {
        this.id = id;
        this.total = total;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.detalleCompras  = new HashSet<DetalleCompra>();
    }

    public Compra(double total, Proveedor proveedor, String fecha) {
        this.total = total;
        this.proveedor = proveedor;
        this.fecha = fecha;
        this.detalleCompras  = new HashSet<DetalleCompra>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Set<DetalleCompra> getDetalleCompras() {
        return detalleCompras;
    }

    public void setDetalleCompras(Set<DetalleCompra> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }

    public void addDetalleCompra(DetalleCompra detalleCompra){
        this.detalleCompras.add(detalleCompra);
    }
}
