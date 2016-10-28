package com.manuel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manuel.model.Cliente;
import com.manuel.model.TipoDocumento;
import com.manuel.model.TipoPersona;
import com.manuel.service.ClienteService;
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
@RequestMapping("api/client")
public class ClientController {

    HttpHeaders headers = new HttpHeaders();
    @Inject ClienteService service;

    @RequestMapping(
            value="/getAll", method = RequestMethod.GET
    )
    public ResponseEntity getAllClients(){
        List<Cliente> clienteList = service.getAll();
        String jsonClienteList = JsonParser.clienteListToJson(clienteList);
        return new ResponseEntity<>(jsonClienteList, headers, HttpStatus.OK);
    }

    @RequestMapping(
        value="/create", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity createClient(@RequestBody Cliente cliente){
        try {
            boolean empresa = false;
            System.out.println(cliente.getNroDocumento());
            List<Cliente> clientes = service.getClienteByNroDocumento(cliente.getNroDocumento());
            for (Cliente clienteEncontrado : clientes){
                System.out.println(clienteEncontrado.getNombre());
                System.out.println(clienteEncontrado.getApellido());
                System.out.println(cliente.getNroDocumento());
                if(cliente.getNroDocumento()==0){
                    empresa = true;
                }
            }
            if(clientes.size()==0){
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getApellido());
                System.out.println(cliente.getDomicilio());
                System.out.println(cliente.getEmail());
                System.out.println(cliente.getNroCuit());
                System.out.println(cliente.getNroDocumento());
                service.saveCliente(cliente);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se creó el cliente con éxito!\"}", headers, HttpStatus.OK);
            }else if(empresa){
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getApellido());
                System.out.println(cliente.getDomicilio());
                System.out.println(cliente.getEmail());
                System.out.println(cliente.getNroCuit());
                System.out.println(cliente.getNroDocumento());
                service.saveCliente(cliente);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se creó el cliente con éxito!\"}", headers, HttpStatus.OK);
            }else{
                System.out.println("success : FALSE");
                return new ResponseEntity<>("{\"message\": \"Ya existe un cliente con ese número de documento!\"}", headers, HttpStatus.OK);
            }
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
    public ResponseEntity updateClient(@RequestBody Cliente cliente){
        try {
            boolean empresa = false;
            System.out.println(cliente.getNroDocumento());
            List<Cliente> clientes = service.getClienteByNroDocumento(cliente.getNroDocumento());
            for (Cliente clienteEncontrado : clientes){
                System.out.println(clienteEncontrado.getNombre());
                System.out.println(clienteEncontrado.getApellido());
                System.out.println(cliente.getNroDocumento());
                if(cliente.getNroDocumento()==0){
                    empresa = true;
                }
            }
            if(clientes.size()==0){
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getApellido());
                System.out.println(cliente.getDomicilio());
                System.out.println(cliente.getEmail());
                System.out.println(cliente.getNroCuit());
                System.out.println(cliente.getNroDocumento());
                service.saveCliente(cliente);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se editó el cliente con éxito!\"}", headers, HttpStatus.OK);
            }else if(empresa){
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getApellido());
                System.out.println(cliente.getDomicilio());
                System.out.println(cliente.getEmail());
                System.out.println(cliente.getNroCuit());
                System.out.println(cliente.getNroDocumento());
                service.saveCliente(cliente);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se editó el cliente con éxito!\"}", headers, HttpStatus.OK);
            }else if(clientes.get(0).getNroDocumento()==cliente.getNroDocumento()){
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getApellido());
                System.out.println(cliente.getDomicilio());
                System.out.println(cliente.getEmail());
                System.out.println(cliente.getNroCuit());
                System.out.println(cliente.getNroDocumento());
                service.saveCliente(cliente);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se editó el cliente con éxito!\"}", headers, HttpStatus.OK);
            }else{
                System.out.println("success : FALSE");
                return new ResponseEntity<>("{\"message\": \"Ya existe un cliente con ese número de documento!\"}", headers, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/tipopersona", method = RequestMethod.GET
    )
    public ResponseEntity<String> getTipoPerona() {
        List<TipoPersona> tipoPersonaList = service.getAllTipoPersona();
        String jsonTipoPersona = JsonParser.tipoPersonaToJson(tipoPersonaList);
        return new ResponseEntity<>(jsonTipoPersona, headers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/tipodocumento", method = RequestMethod.GET
    )
    public ResponseEntity<String> getTipoDocumento() {
        List<TipoDocumento> tipoDocumentoList = service.getAllTipoDocumento();
        String jsonTipoDocumento = JsonParser.tipoDocumentoToJson(tipoDocumentoList);
        return new ResponseEntity<>(jsonTipoDocumento, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<String> searchClients(@RequestParam(value="clientName") String clientName, @RequestParam(value="clientHabilitado") int clientHabilitado){
        try {
            System.out.println(clientName);
            List<Cliente> clienteList = service.getClienteByNameAndHabilitado(clientName, clientHabilitado);
            for (Cliente cli :
                    clienteList) {
                System.out.println(cli.getId());
                System.out.println(cli.getApellido());
                System.out.println(cli.getNombre());
            }
            String jsonClientes = JsonParser.clienteListToJson(clienteList);
            return new ResponseEntity<>(jsonClientes, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/searchByDocument", method = RequestMethod.GET)
    public ResponseEntity<String> searchClientByNroDocumento(@RequestParam(value="nroDocumento") String nroDocumento){
        try {
            boolean empresa = false;
            System.out.println(nroDocumento);
            List<Cliente> clientes = service.getClienteByNroDocumento(Long.valueOf(nroDocumento));
            for (Cliente cliente : clientes){
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getApellido());
                System.out.println(cliente.getNroDocumento());
                if(cliente.getNroDocumento()==0){
                    empresa = true;
                }
            }
            if(clientes.size()==0){
                return new ResponseEntity<>("{\"message\": \"false\"}", headers, HttpStatus.OK);
            }else if(empresa){
                return new ResponseEntity<>("{\"message\": \"false\"}", headers, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("{\"message\": \"true\"}", headers, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }
}
