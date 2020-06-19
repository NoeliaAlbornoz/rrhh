package ar.com.ada.api.rrhh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ar.com.ada.api.rrhh.entities.*;
import ar.com.ada.api.rrhh.models.response.GenericResponse;
import ar.com.ada.api.rrhh.services.*;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias() {
        
        return ResponseEntity.ok(categoriaService.getCategorias());
 
    }

    //GET considera posible pasar nombre por parámetro
    //@GetMapping("/categorias")
    public List<Categoria> getCategorias(@RequestParam(value = "nombre", required = false) String nombre) {
        List<Categoria> lc;

        if (nombre == null) {
            lc = categoriaService.getCategorias();
        } else {
            lc = categoriaService.buscarCategoriasPorNombre(nombre);
        }

        return lc;
    } 

    @PostMapping("/categorias")
    public ResponseEntity<?> crearCategoria(@RequestBody Categoria categoria){

        categoriaService.crearCategoria(categoria);

        return ResponseEntity.ok(categoria);
        
    }

    //POST con GenericResponse
    //@PostMapping("/categorias")
    public ResponseEntity<?> postCategoria(@RequestBody Categoria req) {

        GenericResponse r = new GenericResponse();

        boolean resultado = categoriaService.crearCategoria(req);

        if (resultado) {
            r.isOk = true;
            r.id = req.getCategoriaId();
            r.message = "Creaste una categoría con éxito.";
            return ResponseEntity.ok(r);
        } else {

            r.isOk = false;
            r.message = "No se pudo crear la categoría! ya existe una categoría con ese ID.";

            return ResponseEntity.badRequest().body(r);
        }

    }

}