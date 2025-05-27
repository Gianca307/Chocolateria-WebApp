package ar.com.chocolateria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.ValorAgregado;

@Repository
public interface ValorAgregadoRepository extends JpaRepository<ValorAgregado, Long>{
	@Query("SELECT SUM(v.porcentaje) FROM ValorAgregado v")
	Optional<Double> sumarPorcentajesValorAgregado();
}
