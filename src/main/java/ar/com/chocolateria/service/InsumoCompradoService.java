package ar.com.chocolateria.service;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoComprado;
import ar.com.chocolateria.repository.InsumoCompradoRepository;
import ar.com.chocolateria.repository.InsumoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsumoCompradoService {

	private InsumoCompradoRepository insumoCompradoRepository;
	private InsumoRepository insumoRepository;
	
	public InsumoComprado guardarInsumoComprado (InsumoComprado insumoCompradoNuevo, Long idInsumo) {
		Insumo insumo = insumoRepository.findById(idInsumo).orElseThrow(
				() -> new RuntimeException("No se pudo encontrar el insumo con el id " + idInsumo));
		
		Integer stockNuevo = insumo.getStock() + (insumoCompradoNuevo.getCantidad() * insumo.getCantidad());
		insumo.setStock(stockNuevo);
		
		insumoCompradoNuevo.setInsumo(insumo);
		
		insumoRepository.save(insumo);
		
		return insumoCompradoRepository.save(insumoCompradoNuevo);
	}
	
	public InsumoComprado traerInsumoCompradoById(Long idInsumoComprado) {
		return insumoCompradoRepository.findById(idInsumoComprado).orElseThrow(
				() -> new RuntimeException("No se pudo encontrar el insumo comprado con el id: " +idInsumoComprado));
	}
}
