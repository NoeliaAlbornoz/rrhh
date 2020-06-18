package ar.com.ada.api.rrhh.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.rrhh.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    Categoria findByCategoriaId(Integer categoriaId);

    @Query("select c from Categoria c where c.nombre = ?1")
    List<Categoria> findByNombreCategoria(String nombre);

}