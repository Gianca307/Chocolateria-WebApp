package ar.com.chocolateria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoComprado;

@Repository
public interface InsumoCompradoRepository extends JpaRepository<InsumoComprado, Long> {
	List<InsumoComprado> findByInsumo(Insumo insumo);
}
