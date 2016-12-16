package com.manuel.controller;


import com.manuel.model.*;
import com.manuel.service.CompraService;

import com.manuel.service.ProductService;
import com.manuel.service.ProveedorService;
import com.manuel.util.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/buy")
public class CompraController {
    HttpHeaders headers = new HttpHeaders();
    @Inject
    CompraService service;
    @Inject
    ProveedorService proveedorService;
    @Inject
    ProductService productoService;

    @RequestMapping(
            value="/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity createBuy(@RequestBody String compraString) {
        System.out.println(compraString);
        JSONParser parser = new JSONParser();
        try {
            Object compraObj = parser.parse(compraString);
            JSONObject compraJson = (JSONObject) compraObj ;
            JSONArray productosJson = (JSONArray) compraJson.get("productos");
            JSONObject proveedorJson = (JSONObject) compraJson.get("proveedor");

            double total = (double)compraJson.get("total");
            Proveedor proveedor = proveedorService.getProveedorById((long)proveedorJson.get("id"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            System.out.println(fecha);

            Compra compra = new Compra(total,proveedor,fecha);


            Iterator i = productosJson.iterator();

            while(i.hasNext()){
                JSONObject productoJson = (JSONObject) i.next();
                Producto producto = productoService.getProductoById((long)productoJson.get("id"));
                producto.setPrecio((double)productoJson.get("precio"));
                producto.setPrecioVenta((double)productoJson.get("precioVenta"));
                int stock = producto.getStock();
                stock = stock + ((Long)productoJson.get("cantidad")).intValue();
                producto.setStock(stock);
                productoService.saveProducto(producto);
                DetalleCompra detalleCompra = new DetalleCompra();
                detalleCompra.setProducto(producto);
                detalleCompra.setPrecio(producto.getPrecio());
                detalleCompra.setCantidad(((Long) productoJson.get("cantidad")).intValue());
                detalleCompra.setCompra(compra);

                compra.getDetalleCompras().add(detalleCompra);
            }


            System.out.println(proveedor.getNombre());
            System.out.println(total);

            service.saveCompra(compra);

        }catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("{\"success\": \"true\"}", headers, HttpStatus.OK);
    }
    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<String> searchBuys(@RequestParam(value="buyDate") String buyDate){
        try {
            System.out.println(buyDate);
            List<Compra> compraList = service.getCompraByFecha(buyDate);
            for (Compra comp :
                    compraList) {
                System.out.println(comp.getId());
                System.out.println(comp.getDetalleCompras());
                System.out.println(comp.getFecha());
            }
            String jsonClientes = JsonParser.compraListToJson(compraList);
            return new ResponseEntity<>(jsonClientes, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }
}
