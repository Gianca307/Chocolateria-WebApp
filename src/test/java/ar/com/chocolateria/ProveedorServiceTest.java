package ar.com.chocolateria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; //no hace el import solo

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.chocolateria.domain.Proveedor;
import ar.com.chocolateria.exception.ResourceNotFoundException;
import ar.com.chocolateria.service.ProveedorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional
public class ProveedorServiceTest extends BaseTest{
	private final ProveedorService proveedorService;
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
	}
	
	@Test
	void guardarProveedor () {
		assertNotNull(proveedorGuardado.getId());
		assertEquals("Benjamin", proveedorGuardado.getNombreVendedor());
		assertEquals("2644444444", proveedorGuardado.getTelefonoContacto());
	}
	
	@Test
	void listarProveedores() {
		List<Proveedor> listaProveedores = proveedorService.findAll();
		assertFalse(listaProveedores.isEmpty());
	}
	
	@Test
	void obtenerProveedorPorId() {
		Long idProveedor = proveedorGuardado.getId();
		Proveedor proveedor = proveedorService.findById(idProveedor);
		
		assertNotNull(proveedor); //si o si se usa not null cuando se testea objetos
		assertEquals(idProveedor, proveedor.getId());
	}
	
	@Test
	void eliminarPlataforma() {
		List<Proveedor> proveedorList = proveedorService.findAll();
		assertFalse(proveedorList.isEmpty()); // validamos para poder continuar
		
		Long idUltimoProveedor = proveedorList.get(proveedorList.size()-1).getId();
		
		proveedorService.eliminarPorId(idUltimoProveedor);
		
		Exception exception = assertThrows(ResourceNotFoundException.class, () -> proveedorService.findById(idUltimoProveedor));
		
		String mensajeEsperado = "El proveedor con ese ID no existe " + idUltimoProveedor;
		String mensajeActual = exception.getMessage();
		
		assertTrue(mensajeActual.contains(mensajeEsperado));
	}
	
}
