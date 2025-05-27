package ar.com.chocolateria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.CategoriaProducto;
import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.exception.ResourceNotFoundException;
import ar.com.chocolateria.repository.CategoriaProductoRepository;
import ar.com.chocolateria.repository.InsumoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaProductoService {
	private CategoriaProductoRepository cpr;
	private InsumoRepository ir;
	
	public CategoriaProducto save(CategoriaProducto request) {
		return this.cpr.save(request);
	}
	
	public List<CategoriaProducto> findAll() {
		return this.cpr.findAll();
	}
	
	public CategoriaProducto findById(Long id) {
		CategoriaProducto categoriaProducto = cpr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con ese ID no existe" + id));
		return categoriaProducto;
	}
	
	@Transactional
	public CategoriaProducto actualizarCategoriaProducto(Long id, CategoriaProducto cpRequest){
		CategoriaProducto categoriaProducto = cpr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con ese ID no existe " + id));
		
		categoriaProducto.setCategoria(cpRequest.getCategoria());
		
		CategoriaProducto categoriaActualizada = cpr.save(categoriaProducto);
		
		return categoriaActualizada;
	}

	@Transactional
	public Map<String, Boolean> eliminarPorId(Long id) {
		
		CategoriaProducto categoriaProducto = cpr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con ese ID no existe " + id));
		
		List<Insumo> insumos = this.ir.findByCategoriaProducto(categoriaProducto);
		
		for (Insumo insumo : insumos) {
			insumo.setCategoriaProducto(null);
			ir.save(insumo);
		}
		
		cpr.delete(categoriaProducto);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
