package ar.com.ada.api.rrhh.services;

import java.util.Date;
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
        
        activarEmpleado(empleado);

        grabar(empleado);
        return true;
    }

    public void activarEmpleado(Empleado empleado) {

        empleado.setEstadoId(1);
        empleado.setFechaAlta(new Date());

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

    public boolean actualizarEmpleado(Empleado empleadoOriginal, Empleado empleadoConInfoNueva) {

        empleadoOriginal.setNombre(empleadoConInfoNueva.getNombre());
        empleadoOriginal.setEdad(empleadoConInfoNueva.getEdad());
        empleadoOriginal.getCategoria().setCategoriaId(empleadoConInfoNueva.getCategoria().getCategoriaId());
        empleadoOriginal.setFechaAlta(empleadoConInfoNueva.getFechaAlta());
        empleadoOriginal.setFechaBaja(empleadoConInfoNueva.getFechaBaja());

        grabar(empleadoOriginal);

        return true;
    }

    public boolean actualizarSueldo(Empleado empleadoOriginal, Empleado empleadoConInfoNueva) {

        empleadoOriginal.setSueldo(empleadoConInfoNueva.getSueldo());
        
        grabar(empleadoOriginal);

        return true;
    }

    public boolean actualizarEstado(Empleado empleadoOriginal, Empleado empleadoConInfoNueva) {

        empleadoOriginal.setEstadoId(empleadoConInfoNueva.getEstadoId());
        empleadoOriginal.setFechaBaja(new Date());

        grabar(empleadoOriginal);

        return true;

    }
    
}

