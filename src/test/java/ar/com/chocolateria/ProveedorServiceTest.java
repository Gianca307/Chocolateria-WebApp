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
	void guardarProveedorTest () {
		assertNotNull(proveedorGuardado.getId());
		assertEquals(proveedorGuardado.getNombreEmpresa(), "Jupiter");
		assertEquals(proveedorGuardado.getTelefonoContacto(), "2644444444");
		assertEquals(proveedorGuardado.getNombreVendedor(), "Benjamin");
		assertEquals(proveedorGuardado.getEmail(), "bjupiter@hotmail.com");
		assertEquals(proveedorGuardado.getPaginaWeb(), "www.jupiter.com");
		assertEquals(proveedorGuardado.getDireccion(), "mitre 1520 este");
		assertEquals(proveedorGuardado.getHoraAtencion(), "20:00");
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
	void actualizarProveedorTest() {
		Long idProveedor = 46L;
		Proveedor proveedorActualizado = new Proveedor();
		
		proveedorActualizado.setNombreEmpresa("Cachafaz");
		proveedorActualizado.setTelefonoContacto("4444444442");
		proveedorActualizado.setNombreVendedor("Camila");
		proveedorActualizado.setEmail("cachafaz@hotmail.com");
		proveedorActualizado.setPaginaWeb("www.cachafaz.com");
		proveedorActualizado.setDireccion("algun lugar norte");
		proveedorActualizado.setHoraAtencion("21:00");
		
		Proveedor proveedorDespuesDeActualizar = this.proveedorService.actualizarProveedor(idProveedor, proveedorActualizado);
		
		assertNotNull(proveedorDespuesDeActualizar.getId());
		assertEquals(proveedorDespuesDeActualizar.getNombreEmpresa(), "Cachafaz");
		assertEquals(proveedorDespuesDeActualizar.getTelefonoContacto(), "4444444442");
		assertEquals(proveedorDespuesDeActualizar.getNombreVendedor(), "Camila");
		assertEquals(proveedorDespuesDeActualizar.getEmail(), "cachafaz@hotmail.com");
		assertEquals(proveedorDespuesDeActualizar.getPaginaWeb(), "www.cachafaz.com");
		assertEquals(proveedorDespuesDeActualizar.getDireccion(), "algun lugar norte");
		assertEquals(proveedorDespuesDeActualizar.getHoraAtencion(), "21:00");
	}
	
	@Test
	void eliminarProveedorTest() {
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
