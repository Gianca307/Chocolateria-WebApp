package ar.com.chocolateria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}
