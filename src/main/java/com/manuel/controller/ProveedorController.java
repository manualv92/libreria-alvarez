package com.manuel.controller;

import com.manuel.model.Producto;
import com.manuel.model.Proveedor;
import com.manuel.model.TipoProducto;
import com.manuel.service.ProveedorService;
import com.manuel.util.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/provider")
public class ProveedorController {

    HttpHeaders headers = new HttpHeaders();
    @Inject
    ProveedorService service;


    @RequestMapping(
            value="/getAll", method = RequestMethod.GET
    )
    public ResponseEntity getAllProviders(){
        List<Proveedor> proveederList = service.getAll();
        String jsonProveederList = JsonParser.proveedorListToJson(proveederList);
        return new ResponseEntity<>(jsonProveederList, headers, HttpStatus.OK);
    }

    @RequestMapping(
            value="/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity createProveedor(@RequestBody Proveedor proveedor){
        service.saveProveedor(proveedor);
        return new ResponseEntity<>("{\"message\": \"Se creó el proveedor con éxito!\"}", headers, HttpStatus.OK);
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<String> searchClients(@RequestParam(value="providerName") String providerName, @RequestParam(value="providerHabilitado") int providerHabilitado){
        try {
            System.out.println(providerName);
            List<Proveedor> proveedorList = service.getProveedorByNameAndHabilitado(providerName, providerHabilitado);
            for (Proveedor pro :
                    proveedorList) {
                System.out.println(pro.getId());
                System.out.println(pro.getEmail());
                System.out.println(pro.getNombre());
            }
            String jsonProveedores= JsonParser.proveedorListToJson(proveedorList);
            return new ResponseEntity<>(jsonProveedores, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity updateProvider(@RequestBody Proveedor proveedor){
        try {
            System.out.println(proveedor.getNombre());
            System.out.println(proveedor.getEmail());
            System.out.println(proveedor.getTelefono());
            service.saveProveedor(proveedor);
            return new ResponseEntity<>("{\"success\": \"true\"}", headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }
}
