package ar.com.chocolateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoProducto;
import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.service.ProductoService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductoServiceTest extends BaseTest{
	private final ProductoService productoService;
	private Producto productoGuardado;
	
	@BeforeEach
	void setup () {
		Producto producto = new Producto();
		List<InsumoProducto> insumosProductos = new ArrayList<>();
		Long idCategoriaProducto = 2l;
		
		InsumoProducto insumoProducto1 = new InsumoProducto();
		insumoProducto1.setCantidad(20);
		insumoProducto1.setInsumo(new Insumo());
		insumoProducto1.getInsumo().setId(1L);
		
		InsumoProducto insumoProducto2 = new InsumoProducto();
		insumoProducto2.setCantidad(40);
		insumoProducto2.setInsumo(new Insumo());
		insumoProducto2.getInsumo().setId(2L);
		
		insumosProductos.add(insumoProducto1);
		insumosProductos.add(insumoProducto2);

		producto.setDescripcion("producto de prueba");
		producto.setCostoBase(14589.14f);
		producto.setPrecio(36472.5f);
		producto.setImgUrl("img de prueba");
		producto.setDisponible(true);
		producto.setInsumosProductos(insumosProductos);
		
		productoGuardado = this.productoService.guardarProducto(producto, idCategoriaProducto);
	}
	
	@Test
	void guardarProductoTest() {
		assertNotNull(productoGuardado.getId());
		
		assertFalse(productoGuardado.getInsumosProductos().isEmpty());
		assertEquals(productoGuardado.getCategoriaProductoOferta().getId(), 2L);
		assertEquals(productoGuardado.getDescripcion(), "producto de prueba");
		assertEquals(productoGuardado.getCostoBase(), 14589.14f);
		assertEquals(productoGuardado.getPrecio(), 36472.5f);
		assertEquals(productoGuardado.getImgUrl(), "img de prueba");
		assertTrue(productoGuardado.getDisponible());
	}
	
	@Test
	void listarProductosTest () {
		List<Producto> productos = this.productoService.listarProductos();
		assertFalse(productos.isEmpty());
	}
	
	@Test
	void listarProductosPorCategoriaTest() {
		List<Producto> productos = this.productoService.listarProductosPorCategoria("Chocomensajes");
		
		productos.stream().forEach(producto ->{
			assertTrue(producto.getCategoriaProductoOferta().getCategoria().contains("Chocomensajes"));
		});		
	}
	
	@Test
	void buscarTest () {
		List<Producto> productos = this.productoService.buscar("bom");
		
		productos.stream().forEach(producto -> {
			assertTrue(producto.getDescripcion().toLowerCase().contains("bom"));
		});
	}
	
	@Test
	void actualizarProductoTest() {
		Long idProducto = 1L;
		Long idCategoriaProducto = 3l;
		
		Producto productoActualizado = new Producto();
		List<InsumoProducto> insumosProductos = new ArrayList<>();
		
		InsumoProducto insumoProducto1 = new InsumoProducto();
		insumoProducto1.setCantidad(20);
		insumoProducto1.setInsumo(new Insumo());
		insumoProducto1.getInsumo().setId(1L);
		
		InsumoProducto insumoProducto2 = new InsumoProducto();
		insumoProducto2.setCantidad(40);
		insumoProducto2.setInsumo(new Insumo());
		insumoProducto2.getInsumo().setId(2L);
		
		insumosProductos.add(insumoProducto1);
		insumosProductos.add(insumoProducto2);
		
		productoActualizado.setDescripcion("producto de prueba");
		productoActualizado.setCostoBase(14589.14f);
		productoActualizado.setPrecio(36472.5f);
		productoActualizado.setImgUrl("img de prueba");
		productoActualizado.setDisponible(true);
		productoActualizado.setInsumosProductos(insumosProductos);
		
		Producto productoDespuesDeActualizar = this.productoService.actualizarProducto(idProducto, productoActualizado, idCategoriaProducto);
		
		assertNotNull(productoDespuesDeActualizar.getId());
		
		assertFalse(productoDespuesDeActualizar.getInsumosProductos().isEmpty());
		assertEquals(productoDespuesDeActualizar.getCategoriaProductoOferta().getId(), 3L);
		assertEquals(productoDespuesDeActualizar.getDescripcion(), "producto de prueba");
		assertEquals(productoDespuesDeActualizar.getCostoBase(), 14589.14f);
		assertEquals(productoDespuesDeActualizar.getPrecio(), 36472.5f);
		assertEquals(productoDespuesDeActualizar.getImgUrl(), "img de prueba");
		assertTrue(productoDespuesDeActualizar.getDisponible());
	}
	
	@Test
	void eliminarProductoPorIdTest () {
		List<Producto> productos = this.productoService.listarProductos();
		Long idProducto = productos.get(productos.size() - 1).getId();
		
		this.productoService.eliminarProductoPorId(idProducto);
		
		Exception exception = assertThrows(RuntimeException.class, () -> this.productoService.obtenerProductoPorId(idProducto));
		
		String messageActual = exception.getMessage();
		String messageEsperado = "No se encontró el producto con el id: " + idProducto;
		
		assertTrue(messageActual.contains(messageEsperado));
	}
	
}