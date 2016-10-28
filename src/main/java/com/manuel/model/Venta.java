package com.manuel.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition="Decimal(10,2)")
    private double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String fecha;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private Set<DetalleVenta> detalleVentas;

    public Venta() {}

    public Venta(double total, Cliente cliente, String fecha, Usuario usuario) {
        this.total = total;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detalleVentas = new HashSet<DetalleVenta>();
        this.usuario = usuario;
    }

    public Venta(long id, double total, Cliente cliente, String fecha, Usuario usuario) {
        this.id = id;
        this.total = total;
        this.cliente = cliente;
        this.fecha = fecha;
        this.detalleVentas = new HashSet<DetalleVenta>();
        this.usuario = usuario;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Set<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(Set<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public Usuario getUsuario() { return usuario; }

    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
