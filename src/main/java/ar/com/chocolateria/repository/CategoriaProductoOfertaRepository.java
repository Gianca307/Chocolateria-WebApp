package ar.com.chocolateria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.chocolateria.domain.CategoriaProductoOferta;

@Repository
public interface CategoriaProductoOfertaRepository extends JpaRepository<CategoriaProductoOferta, Long> {
	CategoriaProductoOferta findByCategoria(String categoria);
}
