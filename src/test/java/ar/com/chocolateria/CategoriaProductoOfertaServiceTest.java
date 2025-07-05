package ar.com.chocolateria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.chocolateria.domain.CategoriaProductoOferta;
import ar.com.chocolateria.service.CategoriaProductoOfertaService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaProductoOfertaServiceTest {
	private final CategoriaProductoOfertaService categoriaProductoOfertaService;
	private CategoriaProductoOferta categoriaProductoOfertaGuardado;
	
	@BeforeEach
	void setup () {
		CategoriaProductoOferta categoriaProductoOferta = new CategoriaProductoOferta();
		
		categoriaProductoOferta.setCategoria("Cubanito");
		
		categoriaProductoOfertaGuardado = categoriaProductoOfertaService.save(categoriaProductoOferta);		
	}
	
	@Test
	void saveCategoriaProductoOfertaTest () {
		assertNotNull(categoriaProductoOfertaGuardado.getId());
		assertEquals(categoriaProductoOfertaGuardado.getCategoria(), "Cubanito");
	}
	
	@Test
	void findAllCategoriaProductoOfertaTest() {
		List<CategoriaProductoOferta> categoriasProductosOferta = this.categoriaProductoOfertaService.findAll();
		
		assertFalse(categoriasProductosOferta.isEmpty());		
	}
	
}
