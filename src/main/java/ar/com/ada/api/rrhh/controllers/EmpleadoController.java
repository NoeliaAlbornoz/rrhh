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
    protected EmpleadoService empleadoService;

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

    @PutMapping("/empleados/{id}")
    public ResponseEntity<?> putEmpleado(@PathVariable int id, @RequestBody Empleado req) {

        GenericResponse r = new GenericResponse();

        Empleado empleadoOriginal = empleadoService.buscarPorId(id);

        if (empleadoOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = empleadoService.actualizarEmpleado(empleadoOriginal, req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getEmpleadoId();
            r.message = "Empleado actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo actualizar el empleado.";

            return ResponseEntity.badRequest().body(r);
        }

    }

    @PutMapping("/empleados/{id}/sueldos")
    public ResponseEntity<?> putEmpleadoSueldo(@PathVariable int id, @RequestBody Empleado req) {

        GenericResponse r = new GenericResponse();

        Empleado empleadoOriginal = empleadoService.buscarPorId(id);

        if (empleadoOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = empleadoService.actualizarSueldo(empleadoOriginal, req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getEmpleadoId();
            r.message = "Empleado actualizado con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo actualizar el empleado.";

            return ResponseEntity.badRequest().body(r);
        }

    }

    //Se introduce el cambio de estado desde Frontend
    @PutMapping("/empleados/{id}/estados")
    public ResponseEntity<?> putEmpleadoEstado(@PathVariable int id, @RequestBody int estadoId) {

        GenericResponse r = new GenericResponse();

        Empleado empleadoOriginal = empleadoService.buscarPorId(id);

        if (empleadoOriginal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = empleadoService.actualizarEstado(empleadoOriginal, estadoId);

        if (resultado) {
            r.isOk = true;
            r.id = empleadoOriginal.getEmpleadoId();
            r.message = "Empleado dado de baja con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo dar de baja al empleado.";

            return ResponseEntity.badRequest().body(r);
        }

    }

    @DeleteMapping("empleados/{id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable int id) {

        GenericResponse r = new GenericResponse();

        Empleado empleado = empleadoService.buscarPorId(id);

        if (empleado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean resultado = false;
        resultado = empleadoService.baja(empleado);

        if (resultado) {
            r.isOk = true;
            r.id = id;
            r.message = "Empleado dado de baja con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo dar de baja al empleado.";

            return ResponseEntity.badRequest().body(r);
        }
    
    }


}