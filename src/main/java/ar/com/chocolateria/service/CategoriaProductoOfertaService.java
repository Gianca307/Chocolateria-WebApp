package ar.com.chocolateria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.CategoriaProductoOferta;
import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.exception.ResourceNotFoundException;
import ar.com.chocolateria.repository.CategoriaProductoOfertaRepository;
import ar.com.chocolateria.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaProductoOfertaService {

	private CategoriaProductoOfertaRepository categoriaProductoOfertaRepository;
	private ProductoRepository productoRepository;
	
	public CategoriaProductoOferta save(CategoriaProductoOferta request) {
		return this.categoriaProductoOfertaRepository.save(request);
	}	
	
	public List<CategoriaProductoOferta> findAll() {
		return this.categoriaProductoOfertaRepository.findAll();
	}
	
	public CategoriaProductoOferta findById(Long id) {
		CategoriaProductoOferta categoriaProductoOferta = this.categoriaProductoOfertaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con ese ID no existe" + id));
		return categoriaProductoOferta;
	}
	
	@Transactional
	public CategoriaProductoOferta actualizarCategoriaProductoOferta(Long id, CategoriaProductoOferta cpRequest){
		CategoriaProductoOferta categoriaProductoOferta = this.categoriaProductoOfertaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con ese ID no existe" + id));
		
		categoriaProductoOferta.setCategoria(cpRequest.getCategoria());
		
		CategoriaProductoOferta categoriaActualizada = this.categoriaProductoOfertaRepository.save(categoriaProductoOferta);
		
		return categoriaActualizada;
	}
	
	@Transactional
	public Map<String, Boolean> eliminarPorId(Long id) {
		
		CategoriaProductoOferta categoriaProductoOferta = categoriaProductoOfertaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("La categoria con ese ID no existe" + id));
		
		List<Producto> productos = this.productoRepository.findByCategoriaProductoOferta(categoriaProductoOferta);
		
		productos.forEach(
				producto -> {
				producto.setCategoriaProductoOferta(null);
				productoRepository.save(producto);
				});
		
		categoriaProductoOfertaRepository.delete(categoriaProductoOferta);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
	
}