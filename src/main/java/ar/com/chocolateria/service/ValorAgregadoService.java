package ar.com.chocolateria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.ValorAgregado;
import ar.com.chocolateria.repository.ValorAgregadoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ValorAgregadoService {

	private ValorAgregadoRepository valorAgregadoRepository;
	
	public ValorAgregado save(ValorAgregado valorAgregado) {
		return this.valorAgregadoRepository.save(valorAgregado);
	}
	
	public List<ValorAgregado> findAll(){
		return this.valorAgregadoRepository.findAll();
	}
	
	public Optional<Double> sumarPorcentajesValorAgregado() {
		return this.valorAgregadoRepository.sumarPorcentajesValorAgregado();
	}
	
	public ValorAgregado findById(Long id) {
		ValorAgregado valorAgregadoGuardado = valorAgregadoRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("No se pudo encontrar el Valor Agregado con el id " +id));
		
		return valorAgregadoGuardado;
	}
	
	public ValorAgregado actualizarValorAgregado(Long id, ValorAgregado valorAgregadoActualizado) {
		
		Optional<ValorAgregado> valorAgregadoOptional = valorAgregadoRepository.findById(id);
		
		ValorAgregado valorAgregadoExistente = construirValorAgregado(valorAgregadoActualizado, valorAgregadoOptional);
		
		return valorAgregadoExistente;
	}
	
	public Map<String, Boolean> eliminarValorAgregadoPorId(Long id) {
		
		ValorAgregado valorAgregado = valorAgregadoRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("No se pudo encontrar el Valor Agregado con el id " +id));
		
		
		this.valorAgregadoRepository.delete(valorAgregado);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}

	private ValorAgregado construirValorAgregado(ValorAgregado valorAgregadoActualizado,
			Optional<ValorAgregado> valorAgregadoOptional) {
		
		ValorAgregado.ValorAgregadoBuilder valorAgregadoBuilder = ValorAgregado.builder();
		
		valorAgregadoOptional.ifPresent(valorAgregado -> {
			valorAgregadoBuilder
			.id(valorAgregado.getId())
			.descripcion(valorAgregadoActualizado.getDescripcion())
			.porcentaje(valorAgregadoActualizado.getPorcentaje());
		});
		
		return valorAgregadoBuilder.build();
	}
	
}
