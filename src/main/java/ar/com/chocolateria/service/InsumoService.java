package ar.com.chocolateria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.CategoriaProducto;
import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.InsumoComprado;
import ar.com.chocolateria.domain.InsumoProducto;
import ar.com.chocolateria.domain.Proveedor;
import ar.com.chocolateria.exception.ResourceNotFoundException;
import ar.com.chocolateria.repository.CategoriaProductoRepository;
import ar.com.chocolateria.repository.InsumoCompradoRepository;
import ar.com.chocolateria.repository.InsumoProductoRepository;
import ar.com.chocolateria.repository.InsumoRepository;
import ar.com.chocolateria.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InsumoService {
	private InsumoRepository ir;
	private ProveedorRepository pr;
	private CategoriaProductoRepository cpr;
	private InsumoCompradoRepository icr;
	private InsumoProductoRepository ipr;
	
	@Transactional
	public Insumo save(Insumo insumoNuevo,Long idCategoriaProducto, Long idProveedor) {		
		Proveedor proveedor = this.pr.findById(idProveedor)
				.orElseThrow(() -> new RuntimeException("El proveedor con ese ID no existe " + idProveedor));
		
		insumoNuevo.setProveedor(proveedor);
		
		CategoriaProducto categoriaProducto = this.cpr.findById(idCategoriaProducto)
				.orElseThrow(()-> new RuntimeException("La categoría con el id " + idCategoriaProducto +  " no existe"));
		
		insumoNuevo.setCategoriaProducto(categoriaProducto);
		
		return this.ir.save(insumoNuevo);
	}

	public List<Insumo> findAll() {
		return this.ir.findAll();
	}
	
	public Insumo findById (Long idInsumo) {
		return ir.findById(idInsumo).orElseThrow( () -> 
		new RuntimeException("No se encontró el insumo con el id: " + idInsumo));
	}
	
	@Transactional
	public Map<String, Boolean> eliminarPorId(Long id) {
		Insumo insumo = ir.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El insumo con ese ID no existe" + id));
		
		List<InsumoComprado> insumosComprados = this.icr.findByInsumo(insumo);
		List<InsumoProducto> insumosProductos = this.ipr.findByInsumo(insumo);
		
		insumosComprados.forEach(insumoComprado ->{
			insumoComprado.setInsumo(null);
			this.icr.save(insumoComprado);
		});
		
		insumosProductos.forEach(insumoProducto ->{
			insumoProducto.setInsumo(null);
			this.ipr.save(insumoProducto);
		});		
		
		ir.delete(insumo);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
	
	@Transactional
	public Insumo actualizarInsumo(Long idInsumo, Insumo insumoActualizado, Long idProveedor, Long idCategoriaProducto) {
		Optional<Insumo> insumoOptional = ir.findById(idInsumo);
		
		Proveedor proveedor = pr.findById(idProveedor).orElseThrow(() -> new RuntimeException("No se encontró el proveedor con el id: "+
                idProveedor+" al momento de actualizar el insumo"));
		
		insumoActualizado.setProveedor(proveedor);
		
		CategoriaProducto categoriaProducto = this.cpr.findById(idCategoriaProducto)
				.orElseThrow(()-> new RuntimeException("La categoría con el id " + idCategoriaProducto +  " no existe al momento de actualizar el insumo"));
		
		insumoActualizado.setCategoriaProducto(categoriaProducto);
		
		Insumo insumoExistente = construirInsumo(insumoActualizado, insumoOptional);
		
		return this.ir.save(insumoExistente);
	}
		
	private Insumo construirInsumo(Insumo insumoActualizado, Optional<Insumo> insumoOptional) {
		Insumo.InsumoBuilder insumoBuilder = Insumo.builder();
		
		insumoOptional.ifPresent(insumo -> {
			insumoBuilder
			.id(insumo.getId())
			.descripcion(insumoActualizado.getDescripcion())
			.cantidad(insumoActualizado.getCantidad())
			.unidad(insumoActualizado.getUnidad())
			.costoInsumo(insumoActualizado.getCostoInsumo())
			.stock(insumoActualizado.getStock())
			.img(insumoActualizado.getImg())
			.categoriaProducto(insumoActualizado.getCategoriaProducto())
			.proveedor(insumoActualizado.getProveedor())
			.links(insumoActualizado.getLinks());
		});
		
		return insumoBuilder.build();
	}
}
