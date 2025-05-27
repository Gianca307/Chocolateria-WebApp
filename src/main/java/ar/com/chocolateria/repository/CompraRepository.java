package ar.com.chocolateria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long>{
	@Query("SELECT c FROM Compra c ORDER BY c.fechaCompra ASC")
	List<Compra> findByAllByOrderIgnoreCaseAsc();
}
