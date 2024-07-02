package com.adoptme.petshop.controllers;

import com.adoptme.petshop.entities.User;
import com.adoptme.petshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/users") //transformo ruta a tabla

public class UsersController {

    @Autowired private UsersService service; //importo los servicios

    @PostMapping //solicitud tipo POST
    public ResponseEntity<User> create(@RequestBody User data) { //devuelve el usuario //que depende de la interfaz
        try {
            User user = service.save(data); //guarde ese dato en esa variable
            return new ResponseEntity<>(user, HttpStatus.CREATED); //constructor Response, tiene que construir esa respuesta
            //con el usuario neuvo con el codigo de estado que corresponde a la creacion (201, por defecto de spring)

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //codigo de status
        }
    }

    @GetMapping //solicitud tipo POST
    public ResponseEntity<List<User>> read() {
        try {
            List<User> user = service.read(); //guarde ese dato en esa variable
            return ResponseEntity.ok(user); //forma correcta de responder una lectura (status 200)
            //que pasa si user NO tiene usuarios ??
            //metodo isEmpty() *hacer tarea

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //codigo de status
        }
    }

    @DeleteMapping("/{id}") //solicitud tipo POST
    public ResponseEntity<> destroy(@PathVariable Long id) {
        try {
            service.deleteOne(id);
            //forma correcta de responder una lectura (200)
            //condicionar que pasa si no existe ese usuario
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //codigo de status
        }
    }
}
