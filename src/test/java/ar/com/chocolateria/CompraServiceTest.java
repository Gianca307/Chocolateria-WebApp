package ar.com.chocolateria;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
		
		compra.setInsumosComprados(insumosComprados);
		
		compraGuardada = this.compraService.guardarCompra(compra);		
	}
	
}
