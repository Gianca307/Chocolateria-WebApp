package ar.com.chocolateria.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.domain.Proveedor;
import ar.com.chocolateria.exception.ResourceNotFoundException;
import ar.com.chocolateria.repository.InsumoRepository;
import ar.com.chocolateria.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProveedorService {
	
	private ProveedorRepository pr;
	private InsumoRepository ir;

	public List<Proveedor> findAll() {
		return this.pr.findAll();
	}

	public Proveedor findById(Long id) {
		Proveedor proveedor = this.pr.findById(id).orElseThrow(() -> new ResourceNotFoundException("El proveedor con ese ID no existe " + id));
		return proveedor;
	}

	public Proveedor save(Proveedor proveedor) {
		return this.pr.save(proveedor);
	}
	
	@Transactional
	public Proveedor actualizarProveedor (Long id, Proveedor pv) {
		Proveedor proveedor = this.pr.findById(id).orElseThrow(() -> new ResourceNotFoundException("El proveedor con ese ID no existe " + id));
		
		proveedor.setDireccion(pv.getDireccion());
		proveedor.setEmail(pv.getEmail());
		proveedor.setHoraAtencion(pv.getHoraAtencion());
		proveedor.setNombreEmpresa(pv.getNombreEmpresa());
		proveedor.setNombreVendedor(pv.getNombreVendedor());
		proveedor.setPaginaWeb(pv.getPaginaWeb());
		proveedor.setTelefonoContacto(pv.getTelefonoContacto());
		
		Proveedor proveedorActualizado = this.pr.save(proveedor);
		return proveedorActualizado;
	}
	
	@Transactional
	public Map<String, Boolean> eliminarPorId(Long id) {
		Proveedor proveedor = pr.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El proveedor con ese ID no existe " + id));
		
		List<Insumo> insumos = this.ir.findByProveedor(proveedor);
		
		insumos.forEach(insumo ->{
			insumo.setProveedor(null);
			ir.save(insumo);
		});
		
		pr.delete(proveedor);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
