package ar.com.chocolateria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoProducto;
import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.domain.ProductoVendido;
import ar.com.chocolateria.domain.Venta;
import ar.com.chocolateria.repository.InsumoRepository;
import ar.com.chocolateria.repository.ProductoRepository;
import ar.com.chocolateria.repository.VentaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VentaService {
	
	private ProductoRepository productoRepository;
	private InsumoRepository insumoRepository;
	private VentaRepository ventaRepository;
	
	public Venta obtenerVentaPorId(Long idVenta) {
		return ventaRepository.findById(idVenta)
				.orElseThrow(()-> new RuntimeException("No se pudo encontrar ka venta con el id:" +idVenta));
	}
	
	public List<Venta> listarVentas () {
		return ventaRepository.findAll();
	}
	
	@Transactional
	public Venta guardarVenta (Venta ventaNueva) {
		List<ProductoVendido> productosVendidos = ventaNueva.getProductosVendidos();
		
		for (ProductoVendido pv : productosVendidos) {
			Producto producto = productoRepository.findById(pv.getProducto().getId())
					.orElseThrow(()-> new RuntimeException("No se encotró el producto con el id: " + pv.getProducto().getId()));
			
			for (InsumoProducto ip : producto.getInsumosProductos()) {
				Insumo insumo = insumoRepository.findById(ip.getInsumo().getId())
						.orElseThrow(()-> new RuntimeException("No se encontró el insumo con el id: " + ip.getInsumo().getId()));
				
				Integer stockActualizado = insumo.getStock() - (pv.getCantidad() * ip.getCantidad());
				insumo.setStock(stockActualizado);
			}
			
			pv.setVenta(ventaNueva);
		}
		
		return ventaRepository.save(ventaNueva);
	}
	
	@Transactional
	public Venta actualizarVenta (Venta ventaActualizada, Long idVentaActualizada) {
		Optional<Venta> ventaOptional = ventaRepository.findById(idVentaActualizada);
		
		List<ProductoVendido> productosVendidosGuardados = ventaOptional.get().getProductosVendidos();
		List<ProductoVendido> productosVendidosActualizados = ventaActualizada.getProductosVendidos();
		
		for (ProductoVendido pva : productosVendidosActualizados) {
			Producto producto = productoRepository.findById(pva.getProducto().getId())
					.orElseThrow(()-> new RuntimeException("No se encotró el producto con el id: " + pva.getProducto().getId()));
			pva.setVenta(ventaActualizada);
			
			if(pva.getId() == null) {
				for (InsumoProducto ip : producto.getInsumosProductos()) {
					Insumo insumo = insumoRepository.findById(ip.getInsumo().getId())
							.orElseThrow(()-> new RuntimeException("No se encontró el insumo con el id: " + ip.getInsumo().getId()));
					
					Integer stockActualizado = insumo.getStock() - (pva.getCantidad() * ip.getCantidad());
					insumo.setStock(stockActualizado);
				}
				
				continue;
			} else {
				for (ProductoVendido pvg : productosVendidosGuardados) {
					if(pva.getId().equals(pvg.getId())) {
						if(pva.getCantidad() != pvg.getCantidad()) {
							for (InsumoProducto ip : producto.getInsumosProductos()) {
								Insumo insumoGuardado = insumoRepository.findById(ip.getInsumo().getId())
										.orElseThrow(()-> new RuntimeException("No se encotró el insumo con el id: " + ip.getInsumo().getId()));
								
								Integer stockActualizado = insumoGuardado.getStock() -( (pva.getCantidad() - pvg.getCantidad()) * ip.getCantidad() );
								insumoGuardado.setStock(stockActualizado);
								insumoRepository.save(insumoGuardado);
							}
							break;
						}
						break;
					}
				}
			}
		}
		
		for (ProductoVendido pvg : productosVendidosGuardados) {
			boolean pvgEliminado = true;
			for(ProductoVendido pva : productosVendidosActualizados) {
				if(pvg.getId().equals(pva.getId())) {
					pvgEliminado = false;
				}
			}
			if(pvgEliminado) {
				Producto producto = productoRepository.findById(pvg.getProducto().getId())
						.orElseThrow(()-> new RuntimeException("No se encotró el producto con el id: " + pvg.getProducto().getId()));
				
				for (InsumoProducto ip : producto.getInsumosProductos()) {
					Insumo insumoGuardado = insumoRepository.findById(ip.getInsumo().getId())
							.orElseThrow(()-> new RuntimeException("No se encotró el insumo con el id: " + ip.getInsumo().getId()));
					
					Integer stockActualizado = insumoGuardado.getStock() + (pvg.getCantidad() * ip.getCantidad());
					insumoGuardado.setStock(stockActualizado);
					insumoRepository.save(insumoGuardado);
				}
			}
		}
		
		Venta ventaExistente = construirVenta(ventaActualizada, ventaOptional);
		
		return this.ventaRepository.save(ventaExistente);
	}
	
	
	public void eliminarVenta (Long idVenta) {
		Venta ventaGuardada = ventaRepository.findById(idVenta)
				.orElseThrow(()->new RuntimeException("No se encontró la venta con el id" + idVenta));
		
		for (ProductoVendido pv : ventaGuardada.getProductosVendidos()) {
			Producto producto = productoRepository.findById(pv.getProducto().getId())
					.orElseThrow(()-> new RuntimeException("No se encotró el producto con el id: " + pv.getProducto().getId()));
			for(InsumoProducto ip : producto.getInsumosProductos()) {
				Insumo insumo = insumoRepository.findById(ip.getInsumo().getId())
						.orElseThrow(()-> new RuntimeException("No se encontró el insumo con el id: " + ip.getInsumo().getId()));
				
				Integer stockActualizado = insumo.getStock() + (pv.getCantidad() * ip.getCantidad());
				insumo.setStock(stockActualizado);
				insumoRepository.save(insumo);
			}
		}
		ventaRepository.deleteById(idVenta);
	}

	private Venta construirVenta(Venta ventaActualizada, Optional<Venta> ventaOptional) {
		Venta.VentaBuilder ventaBuilder = Venta.builder();
		
		ventaOptional.ifPresent(venta ->{
			ventaBuilder.id(venta.getId())
			.fechaVenta(ventaActualizada.getFechaVenta())
			.precio(ventaActualizada.getPrecio())
			.productosVendidos(ventaActualizada.getProductosVendidos());
		});
		
		return ventaBuilder.build();
	}
}
