package com.manuel.controller;

import com.manuel.model.Rol;
import com.manuel.model.TipoDocumento;
import com.manuel.model.Usuario;
import com.manuel.service.ClienteService;
import com.manuel.service.UsuarioService;
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
@RequestMapping("api/user")
public class UserController {

    HttpHeaders headers = new HttpHeaders();
    @Inject
    UsuarioService service;
    @Inject
    ClienteService clienteService;

    @RequestMapping(
            value="/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity createUser(@RequestBody Usuario usuario){
        try {

            System.out.println(usuario.getEmail());


            List<Usuario> usuarios = service.getUsuariosByNroDocumento(usuario.getNroDocumento());
            for (Usuario usuarioEncontrado : usuarios){
                System.out.println(usuarioEncontrado.getNombre());
                System.out.println(usuarioEncontrado.getApellido());
                System.out.println(usuarioEncontrado.getNroDocumento());
            }
            if(usuarios.size()==0){
                System.out.println(usuario.getNombre());
                System.out.println(usuario.getApellido());
                System.out.println(usuario.getDomicilio());
                System.out.println(usuario.getEmail());
                System.out.println(usuario.getNroDocumento());
                service.saveUsuario(usuario);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se creó el usuario con éxito!\"}", headers, HttpStatus.OK);
            }else{
                System.out.println("success : FALSE");
                return new ResponseEntity<>("{\"message\": \"Ya existe un usuario con ese número de documento!\"}", headers, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }

    }

    @RequestMapping(
            value="/update", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=application/json"
    )
    public ResponseEntity updateUser(@RequestBody Usuario usuario){
        try {

            System.out.println(usuario.getEmail());


            List<Usuario> usuarios = service.getUsuariosByNroDocumento(usuario.getNroDocumento());
            for (Usuario usuarioEncontrado : usuarios){
                System.out.println(usuarioEncontrado.getNombre());
                System.out.println(usuarioEncontrado.getApellido());
                System.out.println(usuarioEncontrado.getNroDocumento());
            }
            if(usuarios.size()==0){
                System.out.println(usuario.getNombre());
                System.out.println(usuario.getApellido());
                System.out.println(usuario.getDomicilio());
                System.out.println(usuario.getEmail());
                System.out.println(usuario.getNroDocumento());
                service.saveUsuario(usuario);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se editó el usuario con éxito!\"}", headers, HttpStatus.OK);
            }else if(usuarios.get(0).getNroDocumento()==usuario.getNroDocumento()){
                System.out.println(usuario.getNombre());
                System.out.println(usuario.getApellido());
                System.out.println(usuario.getDomicilio());
                System.out.println(usuario.getEmail());
                System.out.println(usuario.getNroDocumento());
                service.saveUsuario(usuario);
                System.out.println("success : TRUE");
                return new ResponseEntity<>("{\"message\": \"Se editó el usuario con éxito!\"}", headers, HttpStatus.OK);
            }else{
                System.out.println("success : FALSE");
                return new ResponseEntity<>("{\"message\": \"Ya existe un usuario con ese número de documento!\"}", headers, HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/tipodocumento", method = RequestMethod.GET
    )
    public ResponseEntity<String> getTipoDocumento() {
        List<TipoDocumento> tipoDocumentoList = clienteService.getAllTipoDocumento();
        String jsonTipoDocumento = JsonParser.tipoDocumentoToJson(tipoDocumentoList);
        return new ResponseEntity<>(jsonTipoDocumento, headers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(
            value="/rol", method = RequestMethod.GET
    )
    public ResponseEntity<String> getRoles() {
        List<Rol> rolList = service.getAllRoles();
        String jsonRolList = JsonParser.rolListToJson(rolList);
        return new ResponseEntity<>(jsonRolList, headers, HttpStatus.OK);
    }

    @RequestMapping(value="/search", method = RequestMethod.GET)
    public ResponseEntity<String> searchUsers(@RequestParam(value="userName") String userName, @RequestParam(value="userHabilitado") int userHabilitado){
        try {
            System.out.println(userName);
            List<Usuario> usuarioList = service.getUsuarioByNameAndHabilitado(userName, userHabilitado);
            for (Usuario cli :
                    usuarioList) {
                System.out.println(cli.getId());
                System.out.println(cli.getApellido());
                System.out.println(cli.getNombre());
            }
            String jsonUsuarios = JsonParser.usuarioListToJson(usuarioList);
            return new ResponseEntity<>(jsonUsuarios, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ResponseEntity<String> login(@RequestParam(value="userNroDocumento") long userNroDocumento, @RequestParam(value="userPassword") String userPassword, @RequestParam(value="userHabilitado") int userHabilitado){
        try {
            System.out.println(userNroDocumento);
            Usuario usuario = service.getUsuarioByNroDocumentoAndHabilitado(userNroDocumento,userHabilitado);
            System.out.println(usuario.getPassword());
            if(usuario.getPassword().equals(userPassword)){
                String jsonUsuario = JsonParser.usuarioToJson(usuario);
                return new ResponseEntity<>(jsonUsuario, headers, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("{\"success\": \"false\"}", headers, HttpStatus.OK);
        }
    }
}
