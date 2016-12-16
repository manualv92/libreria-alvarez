package com.manuel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manuel.model.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by manua on 1/9/2016.
 */
public class JsonParser {

    public static String tipoPersonaToJson(List<TipoPersona> tipoPersonaList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(tipoPersonaList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String tipoDocumentoToJson(List<TipoDocumento> tipoDocumentoList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(tipoDocumentoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String clienteListToJson(List<Cliente> clienteList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(clienteList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String clienteToJson(Cliente cliente) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(cliente);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String productoListToJson(List<Producto> productoList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(productoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String tipoProductoToJson(List<TipoProducto> tipoProductoList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(tipoProductoList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String proveedorListToJson(List<Proveedor> proveedorList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(proveedorList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static Producto jsonProductoToProductoObject(String productoJson){
        Producto producto = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            producto = mapper.readValue(productoJson, Producto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return producto;
    }
    public static Proveedor jsonProveedorToProveedorObject(String proveedorJson){
        Proveedor proveedor = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            proveedor = mapper.readValue(proveedorJson, Proveedor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proveedor;
    }

    public static String rolListToJson(List<Rol> rolList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(rolList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String usuarioListToJson(List<Usuario> usuarioList) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(usuarioList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String usuarioToJson(Usuario usuario) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(usuario);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String compraListToJson(List<Compra> compras) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(compras);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String ventaListToJson(List<Venta> ventas) {
        String jsonString = null;
        try {
            jsonString = new ObjectMapper().writeValueAsString(ventas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
