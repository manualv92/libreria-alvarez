package com.manuel.controller;

import com.manuel.model.*;
import com.manuel.service.*;
import com.manuel.util.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/sell")
public class VentaController {
    HttpHeaders headers = new HttpHeaders();

    @Inject
    VentaService service;
    @Inject
    ClienteService clienteService;
    @Inject
    ProductService productoService;
    @Inject
    UsuarioService usuarioService;

    @RequestMapping(
            value="/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity createSell(@RequestBody String ventaString) {
        System.out.println(ventaString);

        JSONParser parser = new JSONParser();
        try {
            Object ventaObj = parser.parse(ventaString);
            JSONObject ventaJson = (JSONObject) ventaObj ;
            JSONArray productosJson = (JSONArray) ventaJson.get("productos");
            JSONObject clienteJson = (JSONObject) ventaJson.get("cliente");
            JSONObject usuarioJson = (JSONObject) ventaJson.get("usuario");

            Usuario usuario = usuarioService.getUsuarioById((long)usuarioJson.get("id"));
            double total = (double)ventaJson.get("total");
            Cliente cliente = clienteService.getClienteById((long)clienteJson.get("id"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String fecha = dateFormat.format(date);
            System.out.println(fecha);

            Venta venta = new Venta(total,cliente,fecha,usuario);


            Iterator i = productosJson.iterator();

            while(i.hasNext()){
                JSONObject productoJson = (JSONObject) i.next();
                Producto producto = productoService.getProductoById((long)productoJson.get("id"));
                int stock = producto.getStock();
                stock = stock - ((Long)productoJson.get("cantidad")).intValue();
                producto.setStock(stock);
                productoService.saveProducto(producto);
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setProducto(producto);
                detalleVenta.setPrecio(producto.getPrecioVenta());
                detalleVenta.setCantidad(((Long) productoJson.get("cantidad")).intValue());
                detalleVenta.setVenta(venta);

                venta.getDetalleVentas().add(detalleVenta);
            }


            System.out.println(cliente.getNombre());
            System.out.println(total);

            service.saveVenta(venta);

            JasperController jc = new JasperController();

            jc.createJasper(venta);

        }catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("{\"success\": \"true\"}", headers, HttpStatus.OK);
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<String> searchSells(@RequestParam(value="sellDate") String sellDate){
        try {
            System.out.println(sellDate);
            List<Venta> ventaList = service.getCompraByFecha(sellDate);
            for (Venta vent :
                    ventaList) {
                System.out.println(vent.getId());
                System.out.println(vent.getDetalleVentas());
                System.out.println(vent.getFecha());
            }
            String jsonClientes = JsonParser.ventaListToJson(ventaList);
            return new ResponseEntity<>(jsonClientes, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }
}
