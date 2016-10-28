package com.manuel.controller;

import com.manuel.model.Producto;
import com.manuel.model.TipoProducto;
import com.manuel.service.ProductService;
import com.manuel.util.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/product")
public class ProductController {

    HttpHeaders headers = new HttpHeaders();
    @Inject
    ProductService service;

    @RequestMapping(
            value="/getAll", method = RequestMethod.GET
    )
    public ResponseEntity getAllProducts(){
        List<Producto> productoList = service.getAll();
        String jsonProductoList = JsonParser.productoListToJson(productoList);
        return new ResponseEntity<>(jsonProductoList, headers, HttpStatus.OK);
    }

    @RequestMapping(
            value="/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity createProduct(@RequestBody Producto producto){
        service.saveProducto(producto);
        return new ResponseEntity<>("{\"message\": \"Se creó el producto con éxito!\"}", headers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity updateProduct(@RequestBody Producto producto){
        try {
            System.out.println(producto.getNombre());
            System.out.println(producto.getPrecio());
            System.out.println(producto.getStock());
            service.saveProducto(producto);
            return new ResponseEntity<>("{\"success\": \"true\"}", headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<String> searchProducts(@RequestParam(value="productName") String productName, @RequestParam(value="productHabilitado") int productHabilitado){
        try {
            System.out.println(productName);
            List<Producto> productoList = service.getProductoByNameAndHabilitado(productName, productHabilitado);
            for (Producto prod :
                    productoList) {
                System.out.println(prod.getId());
                System.out.println(prod.getNombre());
                System.out.println(prod.getPrecio());
                System.out.println(prod.getStock());
            }
            String jsonProductos = JsonParser.productoListToJson(productoList);
            return new ResponseEntity<>(jsonProductos, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/tipoproducto", method = RequestMethod.GET
    )
    public ResponseEntity<String> getTipoPerona() {
        List<TipoProducto> tipoProductoList = service.getAllTipoProducto();
        String jsonTipoProducto = JsonParser.tipoProductoToJson(tipoProductoList);
        return new ResponseEntity<>(jsonTipoProducto, headers, HttpStatus.OK);
    }
}
