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
		
		compraNueva.getInsumosComprados().forEach(insumoComprado -> {
			Insumo insumoGuardado = insumoRepository.findById(insumoComprado.getInsumo().getId()).orElseThrow(()->
			new RuntimeException("No se pudo encontrar el insumo con el id " + insumoComprado.getInsumo().getId()));
			
			Integer stockNuevo = (insumoComprado.getCantidad() * insumoGuardado.getCantidad()) + insumoGuardado.getStock();
			insumoGuardado.setStock(stockNuevo);
			insumoRepository.save(insumoGuardado);
			insumoComprado.setCompra(compraNueva);
		});
		
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
		compraActualizada.setId(idCompraActualizada);
		
		List<InsumoComprado> insumosCompradosActualizados = compraActualizada.getInsumosComprados();
		List<InsumoComprado> insumosCompradosGuardados = compraOptional.get().getInsumosComprados();
		
		Map<Long, Insumo> insumoCache = new HashMap<>();

		for (InsumoComprado ica : insumosCompradosActualizados) {
		    Long insumoId = ica.getInsumo().getId();
		    
		    Insumo insumoGuardado = insumoCache.computeIfAbsent(insumoId, id -> 
		        insumoRepository.findById(id).orElseThrow(() -> 
		            new RuntimeException("No se pudo encontrar el insumo con el id " + id)
		        )
		    );

		    if (ica.getId() == null) {		     
		        int stockNuevo = (ica.getCantidad() * insumoGuardado.getCantidad()) + insumoGuardado.getStock();
		        insumoGuardado.setStock(stockNuevo);
		    } else {		        
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

		Set<Long> insumosActualizadosIds = insumosCompradosActualizados.stream()
		    .map(InsumoComprado::getId)
		    .filter(Objects::nonNull)
		    .collect(Collectors.toSet());

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
		
		insumosCompradosCancelados.forEach(insumoComprado -> {
			Insumo insumoGuardado = insumoRepository.findById(insumoComprado.getInsumo().getId())
					.orElseThrow(()-> new RuntimeException("No se pudo encontrar el insumo con el id: " + insumoComprado.getInsumo().getId()));
			
			Integer stockCancelado = insumoGuardado.getStock() - (insumoComprado.getCantidad() * insumoGuardado.getCantidad());
			insumoGuardado.setStock(stockCancelado);
			
			insumoRepository.save(insumoGuardado);
		});
		
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
