package ar.com.chocolateria;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; //no hace el import solo
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.chocolateria.domain.CategoriaProducto;
import ar.com.chocolateria.service.CategoriaProductoService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaProductoServiceTest extends BaseTest{
	private final CategoriaProductoService categoriaProductoService;
	
	private CategoriaProducto categoriaProductoGuardado;
	
	@BeforeEach
	void setup () {
		CategoriaProducto categoriaProducto = new CategoriaProducto();
		categoriaProducto.setCategoria("bombones");
		
		categoriaProductoGuardado = categoriaProductoService.save(categoriaProducto);
	};
	
	@Test
	void guardarCategoriaProducto () {
		assertNotNull(categoriaProductoGuardado.getId());
		assertEquals("bombones", categoriaProductoGuardado.getCategoria());
	}
	
	@Test
	void listarCategoriasProductos () {
		List<CategoriaProducto> listaCategoriasProductos = categoriaProductoService.findAll();
		assertFalse(listaCategoriasProductos.isEmpty());
	}
	
	@Test
	void obtenerCategoriaProductoPorId() {
		Long idCategoriaProducto = categoriaProductoGuardado.getId();
		CategoriaProducto categoriaProducto = categoriaProductoService.findById(idCategoriaProducto);
		
		assertNotNull(categoriaProducto);
		assertEquals(idCategoriaProducto, categoriaProducto.getId());
	}
}
