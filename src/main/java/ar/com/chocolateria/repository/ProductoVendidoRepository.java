package ar.com.chocolateria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.domain.ProductoVendido;

@Repository
public interface ProductoVendidoRepository extends JpaRepository<ProductoVendido, Long> {
	List<ProductoVendido> findByProducto(Producto producto);
}
