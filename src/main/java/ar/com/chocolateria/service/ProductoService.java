package ar.com.chocolateria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.CategoriaProductoOferta;
import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.domain.ProductoVendido;
import ar.com.chocolateria.repository.CategoriaProductoOfertaRepository;
import ar.com.chocolateria.repository.ProductoRepository;
import ar.com.chocolateria.repository.ProductoVendidoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductoService {
	
	private ProductoRepository productoRepository;
	private ProductoVendidoRepository productoVendidoRepository;
	private CategoriaProductoOfertaRepository categoriaProductoOfertaRepository;
	
	public Producto obtenerProductoPorId(Long idProducto) {
		return productoRepository.findById(idProducto)
				.orElseThrow(()-> new RuntimeException("No se encontró el producto con el id: " +idProducto));
	}
	
	public List<Producto> listarProductos() {
		return productoRepository.findAll();
	}
	
	public List<Producto> listarProductosPorCategoria(String categoria){
		CategoriaProductoOferta categoriaProductoOferta = categoriaProductoOfertaRepository.findByCategoriaIgnoreCase(categoria);
		
		return productoRepository.findByCategoriaProductoOferta(categoriaProductoOferta);
	}
	
	public List<Producto> buscar(String consulta){		
		return productoRepository.findByDescripcionContainingIgnoreCase(consulta);
	}
	
	public Producto guardarProducto(Producto productoNuevo, Long idCategoriaProductoOferta) {
		CategoriaProductoOferta categoriaProductoOferta = this.categoriaProductoOfertaRepository.findById(idCategoriaProductoOferta)
				.orElseThrow(()-> new RuntimeException("No se encontró la categoria con el id: " + idCategoriaProductoOferta));
		
		productoNuevo.setCategoriaProductoOferta(categoriaProductoOferta);
		
		productoNuevo.getInsumosProductos().forEach(insumoProducto ->{
			insumoProducto.setProducto(productoNuevo);
		});
		
		return productoRepository.save(productoNuevo);
	}
	
	public Producto actualizarProducto(Long idProducto, Producto productoActualizado, Long idCategoriaProductoOferta) {
		Optional<Producto> productoOptional = productoRepository.findById(idProducto);
		
		CategoriaProductoOferta categoriaProductoOferta = this.categoriaProductoOfertaRepository.findById(idCategoriaProductoOferta)
				.orElseThrow(()-> new RuntimeException("No se encontró la categoria con el id: " + idCategoriaProductoOferta));
		
		productoActualizado.setCategoriaProductoOferta(categoriaProductoOferta);
		
		productoActualizado.getInsumosProductos().forEach(insumoProducto -> {
			insumoProducto.setProducto(productoActualizado);
		});
		
		Producto productoExistente = construirProducto(productoActualizado, productoOptional);
		
		return productoRepository.save(productoExistente);
	}
	
	public void eliminarProductoPorId (Long idProducto) {
		Producto productoAEliminar = this.productoRepository.findById(idProducto)
				.orElseThrow(() -> new RuntimeException("No se pudo encontrar el producto con el id " + idProducto));
		
		List<ProductoVendido> productosVendidos = this.productoVendidoRepository.findByProducto(productoAEliminar);
		
		productosVendidos.forEach(productoVendido ->{
			productoVendido.setProducto(null);
			this.productoVendidoRepository.save(productoVendido);
		});
		
		productoRepository.deleteById(idProducto);
	}

	private Producto construirProducto(Producto productoActualizado, Optional<Producto> productoOptional) {
		Producto.ProductoBuilder productoBuilder = Producto.builder();
		
		productoOptional.ifPresent(producto -> {
			productoBuilder
			.id(producto.getId())
			.descripcion(productoActualizado.getDescripcion())
			.precio(productoActualizado.getPrecio())
			.costoBase(productoActualizado.getCostoBase())
			.imgUrl(productoActualizado.getImgUrl())
			.disponible(productoActualizado.getDisponible())
			.categoriaProductoOferta(productoActualizado.getCategoriaProductoOferta())
			.insumosProductos(productoActualizado.getInsumosProductos());
		});
		
		return productoBuilder.build();
	}
}
