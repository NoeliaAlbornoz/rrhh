package ar.com.ada.api.rrhh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.rrhh.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Empleado findByEmpleadoId(Integer empleadoId);

    @Query("select e from Empleado e where e.nombre = ?1")
    List<Empleado> findByNombreEmpleado(String nombre); 

    @Query("Select e FROM Empleado e WHERE e.categoria.categoriaId = :categoriaId")
    List<Empleado> findAllByCategoriaId(@Param("categoriaId") int categoriaId);

}