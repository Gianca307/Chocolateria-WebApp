package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.chocolateria.domain.Proveedor;
import ar.com.chocolateria.service.ProveedorService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/proveedores")
public class ProveedorViewController {
	
	private final ProveedorService proveedorService;
	
	@GetMapping("/listar")
	public String listarProveedores (Model model) {
		model.addAttribute("proveedores" , proveedorService.findAll());
		
		return "listaProveedores";
	}
	
	@GetMapping("/agregarProveedor")
	public String mostrarFormularioAgregarProveedor (Model model) {
		model.addAttribute("proveedor",new Proveedor());		
		return "agregarProveedorForm";
	}
	
	@PostMapping("/guardarProveedor")
	public String guardarProveedor (@ModelAttribute Proveedor proveedor) {
		proveedorService.save(proveedor);
		return "redirect:/proveedores/listar";
	}
	
	@GetMapping("/actualizarProveedor/{id}")
	public String actualizarProveedor(@PathVariable Long id, Model model) {
		model.addAttribute("proveedor", proveedorService.findById(id));
		return "actualizarProveedorForm";
	}
	
	@PostMapping("/actualizarProveedor/{idProveedor}")
	public String actualizarProveedor(@PathVariable Long idProveedor, @ModelAttribute Proveedor proveedor) {
		proveedorService.actualizarProveedor(idProveedor, proveedor);
		return "redirect:/proveedores/listar";
	}
	
	@GetMapping("/eliminarProveedor/{id}")
	public String eliminarProveedor (@PathVariable Long id) {
		proveedorService.eliminarPorId(id);
		return "redirect:/proveedores/listar";
	}
}
