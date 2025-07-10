package ar.com.chocolateria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.domain.ProductoVendido;
import ar.com.chocolateria.domain.Venta;
import ar.com.chocolateria.service.VentaService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VentaServiceTest extends BaseTest{
	private final VentaService ventaService;
	private Venta ventaGuardada;
	
	@BeforeEach
	void setup () {
		Venta venta = new Venta();
		List<ProductoVendido> productosVendidos = new ArrayList<>();
		
		ProductoVendido productoVendido = new ProductoVendido();
		productoVendido.setCantidad(5);
		productoVendido.setPrecio(13600f);
		Producto producto = new Producto();
		producto.setId(15L);
		productoVendido.setProducto(producto);
		
		ProductoVendido productoVendido1 = new ProductoVendido();
		productoVendido1.setCantidad(2);
		productoVendido1.setPrecio(32000f);
		Producto producto1 = new Producto();
		producto1.setId(16L);
		productoVendido1.setProducto(producto1);
		
		productosVendidos.add(productoVendido1);
		productosVendidos.add(productoVendido);
		
		venta.setFechaVenta(LocalDate.now());
		venta.setPrecio(68000f);
		venta.setProductosVendidos(productosVendidos);
		
		ventaGuardada = this.ventaService.guardarVenta(venta);
	}
	
	@Test
	void guardarVentaTest () {
		assertNotNull(ventaGuardada.getId());
		
		assertEquals(ventaGuardada.getFechaVenta(), LocalDate.now());
		assertEquals(ventaGuardada.getPrecio(), 68000f);
		assertFalse(ventaGuardada.getProductosVendidos().isEmpty());
	}
	
	@Test
	void listarVentasTest() {
		List<Venta> ventas = this.ventaService.listarVentas();
		assertFalse(ventas.isEmpty());
	}
	
	@Test
	void actualizarVentaTest() {
		Long idVenta = 1L;
		Venta ventaActualizada = new Venta();
		List<ProductoVendido> productosVendidos = new ArrayList<>();
		
		ProductoVendido productoVendido = new ProductoVendido();
		productoVendido.setCantidad(5);
		productoVendido.setPrecio(6499.99f);
		Producto producto = new Producto();
		producto.setId(1L);
		productoVendido.setProducto(producto);
		
		ProductoVendido productoVendido1 = new ProductoVendido();
		productoVendido1.setCantidad(2);
		productoVendido1.setPrecio(14400f);
		Producto producto1 = new Producto();
		producto1.setId(2L);
		productoVendido1.setProducto(producto1);
		
		productosVendidos.add(productoVendido1);
		productosVendidos.add(productoVendido);
		
		ventaActualizada.setId(idVenta);
		ventaActualizada.setFechaVenta(LocalDate.now());
		ventaActualizada.setPrecio(28800f);
		ventaActualizada.setProductosVendidos(productosVendidos);
		
		Venta ventaDespuesDeActualizar = this.ventaService.actualizarVenta(ventaActualizada, idVenta);
		
		assertNotNull(ventaDespuesDeActualizar.getId());
		
		assertEquals(ventaDespuesDeActualizar.getFechaVenta(), LocalDate.now());
		assertEquals(ventaDespuesDeActualizar.getPrecio(), 28800f);
		assertFalse(ventaDespuesDeActualizar.getProductosVendidos().isEmpty());
	}
	
}
