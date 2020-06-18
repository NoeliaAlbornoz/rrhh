package ar.com.ada.api.rrhh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.api.rrhh.entities.*;
import ar.com.ada.api.rrhh.models.response.GenericResponse;
import ar.com.ada.api.rrhh.services.*;

@RestController
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Empleado> le;

        if (nombre == null) {
            le = empleadoService.getEmpleados();
        } else {
            le = empleadoService.buscarEmpleadosPorNombre(nombre);
        }

        return le;
    } 

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> getEmpleadoById(@PathVariable int id) {
        Empleado e = empleadoService.buscarPorId(id);

        if (e == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(e);
    }

    @PostMapping("/empleados")
    public ResponseEntity<?> postEmpleado(@RequestBody Empleado req) {

        GenericResponse r = new GenericResponse();

        boolean resultado = empleadoService.crearEmpleado(req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getEmpleadoId();
            r.message = "Creaste un empleado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo crear un empleado! ya existe un empleado con ese ID.";

            return ResponseEntity.badRequest().body(r);
        }

    }

    @GetMapping("/empleados/categorias/{categoriaId}")
    public List<Empleado> getEmpleadosByCategoriaId(@PathVariable int categoriaId) {
        List<Empleado> le;
        
            le = empleadoService.buscarEmpleadosPorCategoriaId(categoriaId);
        
        return le;
    }

}