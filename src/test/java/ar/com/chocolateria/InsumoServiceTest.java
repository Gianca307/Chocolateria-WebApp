package ar.com.chocolateria;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; //no hace el import solo

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ar.com.chocolateria.domain.CategoriaProducto;
import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.Proveedor;
import ar.com.chocolateria.service.CategoriaProductoService;
import ar.com.chocolateria.service.InsumoService;
import ar.com.chocolateria.service.ProveedorService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class InsumoServiceTest extends BaseTest{
	private final InsumoService insumoService;
	private final ProveedorService proveedorService;
	private final CategoriaProductoService categoriaProductoService;
	
	private Insumo insumoTemporal;
	private CategoriaProducto categoriaProductoGuardado;
	private Proveedor proveedorGuardado;
	
	@BeforeEach
	void setup() {
		
		Proveedor proveedor = new Proveedor();
		
		proveedor.setNombreEmpresa("Jupiter");
		proveedor.setTelefonoContacto("2644444444");
		proveedor.setNombreVendedor("Benjamin");
		proveedor.setEmail("bjupiter@hotmail.com");
		proveedor.setPaginaWeb("www.jupiter.com");
		proveedor.setDireccion("mitre 1520 este");
		proveedor.setHoraAtencion("20:00");
		
		proveedorGuardado = proveedorService.save(proveedor);
		
		CategoriaProducto categoriaProducto = new CategoriaProducto();
		categoriaProducto.setCategoria("bombones");
		
		categoriaProductoGuardado = categoriaProductoService.save(categoriaProducto);
		
		String enlace = "enlace 1";
		String enlace2 = "enlace 2";
		
		List<String> enlaces = new ArrayList<String>();
		
		enlaces.add(enlace);
		enlaces.add(enlace2);
		
		insumoTemporal = new Insumo();
		insumoTemporal.setDescripcion("Bombones");
		insumoTemporal.setCantidad(5);
		insumoTemporal.setUnidad("gramos");
		insumoTemporal.setCostoInsumo(154.17f);
		insumoTemporal.setStock(15);
		insumoTemporal.setImg("img src");
		insumoTemporal.setLinks(enlaces);
	}
	
	@Test
	void guardarInsumo () {
		insumoTemporal.setProveedor(proveedorGuardado);
		insumoTemporal.setCategoriaProducto(categoriaProductoGuardado);
		Insumo insumoGuardado = insumoService.save(insumoTemporal, categoriaProductoGuardado.getId(), proveedorGuardado.getId());
		
		assertNotNull(insumoGuardado.getId());
		assertEquals(insumoGuardado.getDescripcion(), "Bombones");
		assertEquals(insumoGuardado.getCantidad(), 5);
		assertEquals(insumoGuardado.getUnidad(), "gramos");
		assertEquals(insumoGuardado.getCostoInsumo(), 154.17f);
		assertEquals(insumoGuardado.getStock(), 15);
		assertEquals(insumoGuardado.getImg(), "img src");
		
		assertFalse(insumoGuardado.getLinks().isEmpty());
	}
	
	@Test
	void listarInsumos () {
		assertFalse(insumoService.findAll().isEmpty());
	}
	
	@Test
	void actualizarInsumo () {
		System.out.println();
		Insumo insumoGuardado = insumoService.save(insumoTemporal, categoriaProductoGuardado.getId(), proveedorGuardado.getId());
		
		Insumo insumoActualizado = new Insumo();
		insumoActualizado.setDescripcion("Chocolatines");
		insumoActualizado.setCantidad(11);
		insumoActualizado.setUnidad("kilos");
		insumoActualizado.setCostoInsumo(14447.17f);
		insumoActualizado.setStock(6);
		insumoActualizado.setImg("img src de chocolatines");
		
		insumoActualizado.setProveedor(proveedorGuardado);
		insumoActualizado.setCategoriaProducto(categoriaProductoGuardado);
		
		insumoService.actualizarInsumo(insumoGuardado.getId(), insumoActualizado, proveedorGuardado.getId(), categoriaProductoGuardado.getId());
		
		Insumo insumoDespuesDeActualizar = insumoService.findById(insumoGuardado.getId());
		
		//assertEquals(insumoActualizado.getId(), insumoDespuesDeActualizar.getId());
		assertEquals(insumoActualizado.getDescripcion(), insumoDespuesDeActualizar.getDescripcion());
		assertEquals(insumoActualizado.getCantidad(), insumoDespuesDeActualizar.getCantidad());
		assertEquals(insumoActualizado.getUnidad(), insumoDespuesDeActualizar.getUnidad());
		assertEquals(insumoActualizado.getCostoInsumo(), insumoDespuesDeActualizar.getCostoInsumo());
		assertEquals(insumoActualizado.getStock(), insumoDespuesDeActualizar.getStock());
		assertEquals(insumoActualizado.getImg(), insumoDespuesDeActualizar.getImg());
		
		assertEquals(insumoActualizado.getProveedor().getId(), insumoDespuesDeActualizar.getProveedor().getId());
		assertEquals(insumoActualizado.getCategoriaProducto().getId(), insumoDespuesDeActualizar.getCategoriaProducto().getId());
	}
	
	@Test
	void obtenerInsumoPorId() {
		Long idInsumo = 1L;
		
		Insumo insumoGuardado = insumoService.findById(idInsumo);
		
		assertNotNull(insumoGuardado);
		assertEquals(insumoGuardado.getId(), idInsumo);
	}
	
	@Test
	void eliminarInsumoPorId () {
		List<Insumo> listaInsumos = insumoService.findAll();
		assertFalse(listaInsumos.isEmpty());
		
		Long idInsumoAEliminar = listaInsumos.get(listaInsumos.size()-1).getId();
		
		insumoService.eliminarPorId(idInsumoAEliminar);
		
		Exception exception = assertThrows(RuntimeException.class, () -> insumoService.findById(idInsumoAEliminar));
		
		String mensajeEsperado = "No se encontró el insumo con el id: " + idInsumoAEliminar;
		String mensajeActual = exception.getMessage();
		
		assertTrue(mensajeEsperado.contains(mensajeActual));
	}
}
