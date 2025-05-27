package ar.com.chocolateria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoProducto;

@Repository
public interface InsumoProductoRepository extends JpaRepository<InsumoProducto, Long> {
	List<InsumoProducto> findByInsumo(Insumo insumo);
}
