package ar.com.ada.api.rrhh.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.rrhh.entities.Empleado;

import ar.com.ada.api.rrhh.repos.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepo;

    public List<Empleado> getEmpleados() {

        return empleadoRepo.findAll();
    }

    public List<Empleado> buscarEmpleadosPorNombre(String nombre) {

        return empleadoRepo.findByNombreEmpleado(nombre);
    } 

    public boolean crearEmpleado(Empleado empleado) {

        if (existe(empleado.getEmpleadoId()))
            return false;

        grabar(empleado);
        return true;
    }

    public void grabar(Empleado empleado) {

        empleadoRepo.save(empleado);
    }

    public boolean existe(int empleadoId) {

        Empleado empleado = buscarPorId(empleadoId);

        return empleado != null;

    }

    public Empleado buscarPorId(int empleadoId) {

        return empleadoRepo.findByEmpleadoId(empleadoId);
    }

    public List<Empleado> buscarEmpleadosPorCategoriaId(int categoriaId) {

        return empleadoRepo.findAllByCategoriaId(categoriaId);
    }
    
}

