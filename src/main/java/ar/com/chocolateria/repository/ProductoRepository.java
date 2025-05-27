package ar.com.chocolateria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.CategoriaProductoOferta;
import ar.com.chocolateria.domain.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	List<Producto> findByCategoriaProductoOferta(CategoriaProductoOferta categoriaProductoOferta);
	
	List<Producto> findByDescripcionContainingIgnoreCase(String consulta);
}
