package ar.com.ada.api.rrhh.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.rrhh.entities.Categoria;
import ar.com.ada.api.rrhh.entities.Empleado;
import ar.com.ada.api.rrhh.repos.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    protected CategoriaRepository categoriaRepo;

    public List<Categoria> getCategorias() {

        return categoriaRepo.findAll();
    }

    public List<Categoria> buscarCategoriasPorNombre(String nombre) {

        return categoriaRepo.findByNombreCategoria(nombre);
    }

    public boolean crearCategoria(Categoria categoria) {

        if (existe(categoria.getCategoriaId()))
            return false;

        grabar(categoria);
        return true;
    }

    public void grabar(Categoria categoria) {

        categoriaRepo.save(categoria);
    }

    public boolean existe(int categoriaId) {

        Categoria categoria = buscarPorId(categoriaId);

        return categoria != null;

    }

    public Categoria buscarPorId(int categoriaId) {

        return categoriaRepo.findByCategoriaId(categoriaId);
    }

    //Utilizamos directamente el método findById()
    public List<Empleado> traerEmpleadosPorCategoria(int categoriaId){

        Optional<Categoria> cOptional = categoriaRepo.findById(categoriaId);
        List<Empleado> listaVacia = new ArrayList<>();
        
        if(cOptional.isPresent()){

            return (cOptional.get()).getEmpleados();
        }
        return listaVacia;

    }
    
}

