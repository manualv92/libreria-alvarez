package com.manuel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by manua on 22/8/2016.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nombre;
    private String apellido;
    private String domicilio;
    private String email;
    private int habilitado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_documento")
    private TipoDocumento tipoDocumento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_persona")
    private TipoPersona tipoPersona;
    private long nroDocumento;
    private long nroCuit;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "cliente")
    @JsonIgnore
    private List<Venta> ventas;

    protected Cliente(){}

    public Cliente(int id, String nombre, String apellido, String domicilio, String email, int habilitado, TipoDocumento tipoDocumento, TipoPersona tipoPersona, long nroDocumento, long nroCuit) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.domicilio = domicilio;
        this.email = email;
        this.habilitado = habilitado;
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

    public long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public long getNroCuit() {
        return nroCuit;
    }

    public void setNroCuit(long nroCuit) {
        this.nroCuit = nroCuit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
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

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @JsonIgnore
    public List<Venta> getVentas() {
        return ventas;
    }

    @JsonIgnore
    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }
}
