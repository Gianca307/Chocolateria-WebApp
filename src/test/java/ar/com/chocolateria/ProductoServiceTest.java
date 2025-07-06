package ar.com.chocolateria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.service.ProductoService;
import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductoServiceTest {
	private final ProductoService productoService;
	private Producto productoGuardado;
	
}
