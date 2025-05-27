package ar.com.chocolateria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.Compra;
import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoComprado;
import ar.com.chocolateria.repository.CompraRepository;
import ar.com.chocolateria.repository.InsumoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompraService {
	private CompraRepository compraRepository;
	private InsumoRepository insumoRepository;
	
	@Transactional
	public Compra guardarCompra (Compra compraNueva) {
		for (InsumoComprado ic : compraNueva.getInsumosComprados()) {
			Insumo insumoGuardado = insumoRepository.findById(ic.getInsumo().getId()).orElseThrow(()->
			new RuntimeException("No se pudo encontrar el insumo con el id " + ic.getInsumo().getId()));
			
			Integer stockNuevo = (ic.getCantidad() * insumoGuardado.getCantidad()) + insumoGuardado.getStock();
			insumoGuardado.setStock(stockNuevo);
			insumoRepository.save(insumoGuardado);
			ic.setCompra(compraNueva);
		}
		
		return compraRepository.save(compraNueva);
	}
	
	public Compra obtenerCompraPorId (Long idCompra) {
		return compraRepository.findById(idCompra)
				.orElseThrow(()-> new RuntimeException("No se pudo encontrar la compra con ese id: " + idCompra));
	}
	
	public List<Compra> listarCompras(){
		return compraRepository.findByAllByOrderIgnoreCaseAsc();
	}
	
	@Transactional
	public Compra actualizarCompra (Long idCompraActualizada, Compra compraActualizada) {
		Optional<Compra> compraOptional = compraRepository.findById(idCompraActualizada);
		
		List<InsumoComprado> insumosCompradosActualizados = compraActualizada.getInsumosComprados();
		List<InsumoComprado> insumosCompradosGuardados = compraOptional.get().getInsumosComprados();
		
		Map<Long, Insumo> insumoCache = new HashMap<>();

		// Primera iteración: procesar insumos actualizados
		for (InsumoComprado ica : insumosCompradosActualizados) {
		    Long insumoId = ica.getInsumo().getId();
		    
		    // Obtener insumo de cache o de la base de datos
		    Insumo insumoGuardado = insumoCache.computeIfAbsent(insumoId, id -> 
		        insumoRepository.findById(id).orElseThrow(() -> 
		            new RuntimeException("No se pudo encontrar el insumo con el id " + id)
		        )
		    );

		    if (ica.getId() == null) {
		        // Nuevo insumo comprado
		        int stockNuevo = (ica.getCantidad() * insumoGuardado.getCantidad()) + insumoGuardado.getStock();
		        insumoGuardado.setStock(stockNuevo);
		    } else {
		        // Modificación de insumo comprado
		        insumosCompradosGuardados.stream()
		            .filter(icg -> ica.getId().equals(icg.getId()) && !icg.getCantidad().equals(ica.getCantidad()))
		            .findFirst()
		            .ifPresent(icg -> {
		                int stockModificado = insumoGuardado.getStock() - (icg.getCantidad() * insumoGuardado.getCantidad()) +
		                                      (ica.getCantidad() * insumoGuardado.getCantidad());
		                insumoGuardado.setStock(stockModificado);
		            });
		    }
		    
		    insumoRepository.save(insumoGuardado);
		    ica.setCompra(compraActualizada);
		}

		// Crear un set con los IDs de los insumos actualizados para detección rápida
		Set<Long> insumosActualizadosIds = insumosCompradosActualizados.stream()
		    .map(InsumoComprado::getId)
		    .filter(Objects::nonNull)
		    .collect(Collectors.toSet());

		// Segunda iteración: eliminar insumos que no están en la nueva lista
		for (InsumoComprado icg : insumosCompradosGuardados) {
		    if (!insumosActualizadosIds.contains(icg.getId())) {
		        Long insumoId = icg.getInsumo().getId();
		        
		        Insumo insumoGuardado = insumoCache.computeIfAbsent(insumoId, id -> 
		            insumoRepository.findById(id).orElseThrow(() -> 
		                new RuntimeException("No se pudo encontrar el insumo con el id " + id)
		            )
		        );
		        
		        int stockModificado = insumoGuardado.getStock() - (icg.getCantidad() * insumoGuardado.getCantidad());
		        insumoGuardado.setStock(stockModificado);
		        insumoRepository.save(insumoGuardado);
		    }
		}
		
		Compra compraExistente = construirCompra(compraOptional, compraActualizada);
		
		return compraRepository.save(compraExistente);		
	}
	
	@Transactional
	public void eliminarCompraPorId(Long id) {
		Compra compraGuardada = compraRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("No se pudo encontrar la compra con el id: " + id));
		
		List<InsumoComprado> insumosCompradosCancelados = compraGuardada.getInsumosComprados();
		
		for(InsumoComprado ic: insumosCompradosCancelados) {
			Insumo insumoGuardado = insumoRepository.findById(ic.getInsumo().getId())
					.orElseThrow(()-> new RuntimeException("No se pudo encontrar el insumo con el id: " + ic.getInsumo().getId()));
			
			Integer stockCancelado = insumoGuardado.getStock() - (ic.getCantidad() * insumoGuardado.getCantidad());
			insumoGuardado.setStock(stockCancelado);
			
			insumoRepository.save(insumoGuardado);
		}
		
		compraRepository.deleteById(id);
	}

	private Compra construirCompra(Optional<Compra> compraOptional, Compra compraActualizada) {
		Compra.CompraBuilder compraBuilder = Compra.builder();
		
		compraOptional.ifPresent(compra ->{
			compraBuilder
			.id(compra.getId())
			.fechaCompra(compraActualizada.getFechaCompra())
			.precio(compraActualizada.getPrecio())
			.insumosComprados(compraActualizada.getInsumosComprados());
		});
		return compraBuilder.build();
	}
}
