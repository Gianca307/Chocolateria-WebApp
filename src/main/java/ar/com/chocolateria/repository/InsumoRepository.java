package ar.com.chocolateria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.CategoriaProducto;
import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.Proveedor;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
	List<Insumo> findByProveedor(Proveedor proveedor);
	
	List<Insumo> findByCategoriaProducto(CategoriaProducto categoriaProducto);
}
