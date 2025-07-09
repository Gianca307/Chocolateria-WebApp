package ar.com.chocolateria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.chocolateria.domain.Compra;
import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoComprado;
import ar.com.chocolateria.service.CompraService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompraServiceTest {
	private final CompraService compraService;
	private Compra compraGuardada;
	
	@BeforeEach
	void setup() {
		Compra compra = new Compra();
		List<InsumoComprado> insumosComprados = new ArrayList<>();
		
		InsumoComprado insumoComprado1 = new InsumoComprado();
		insumoComprado1.setCantidad(3);
		insumoComprado1.setPrecio(9149.49f);
		Insumo insumo = new Insumo();
		insumo.setId(2L);
		insumoComprado1.setInsumo(insumo);
		
		InsumoComprado insumoComprado2 = new InsumoComprado();
		insumoComprado2.setCantidad(10);
		insumoComprado2.setPrecio(1199f);
		Insumo insumo1 = new Insumo();
		insumo1.setId(3L);
		insumoComprado2.setInsumo(insumo);
		
		insumosComprados.add(insumoComprado1);
		insumosComprados.add(insumoComprado2);
		
		compra.setPrecio(39438.47f);
		compra.setFechaCompra(LocalDate.of(2025, 3, 17));
		compra.setInsumosComprados(insumosComprados);
		
		compraGuardada = this.compraService.guardarCompra(compra);		
	}
	
	@Test
	void guardarCompraTest() {
		assertNotNull(compraGuardada.getId());
		assertFalse(compraGuardada.getInsumosComprados().isEmpty());
		assertEquals(compraGuardada.getFechaCompra(), LocalDate.of(2025, 3, 17));
		assertEquals(compraGuardada.getPrecio(), 39438.47f);
	}
	
	@Test
	void listarComprasTest () {
		List<Compra> compras = this.compraService.listarCompras();		
		assertFalse(compras.isEmpty());
	}
	
	@Test
	void actualizarCompraTest() {
		Long idCompra = 1L;
		
		Compra compra = new Compra();
		List<InsumoComprado> insumosComprados = new ArrayList<>();
		
		InsumoComprado insumoComprado1 = new InsumoComprado();
		insumoComprado1.setCantidad(3);
		insumoComprado1.setPrecio(5301f);
		Insumo insumo = new Insumo();
		insumo.setId(5L);
		insumoComprado1.setInsumo(insumo);
		
		InsumoComprado insumoComprado2 = new InsumoComprado();
		insumoComprado2.setCantidad(10);
		insumoComprado2.setPrecio(3199f);
		Insumo insumo1 = new Insumo();
		insumo1.setId(7L);
		insumoComprado2.setInsumo(insumo);
		
		insumosComprados.add(insumoComprado1);
		insumosComprados.add(insumoComprado2);
		
		compra.setPrecio(31990f);
		compra.setFechaCompra(LocalDate.of(2025, 3, 17));
		compra.setInsumosComprados(insumosComprados);
		
		Compra compraDespuesDeActualizar = this.compraService.actualizarCompra(idCompra, compra);
		
		assertNotNull(compraDespuesDeActualizar.getId());
		assertFalse(compraDespuesDeActualizar.getInsumosComprados().isEmpty());
		assertEquals(compraDespuesDeActualizar.getFechaCompra(), LocalDate.of(2025, 3, 17));
		assertEquals(compraDespuesDeActualizar.getPrecio(), 31990f);
	}
	
}