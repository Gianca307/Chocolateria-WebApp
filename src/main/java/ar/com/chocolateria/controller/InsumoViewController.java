package ar.com.chocolateria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.chocolateria.domain.Insumo;
import ar.com.chocolateria.service.CategoriaProductoService;
import ar.com.chocolateria.service.InsumoService;
import ar.com.chocolateria.service.ProveedorService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/insumos")
public class InsumoViewController {
	private final InsumoService insumoService;
	private final CategoriaProductoService categoriaProductoService;
	private final ProveedorService proveedorService;
	
	@GetMapping("/listar")
	public String listarInsumos(Model model) {
		model.addAttribute("insumos", this.insumoService.findAll());
		return "listaInsumos";
	}
	
	@GetMapping("/agregarInsumo")
	public String mostrarFormularioInsumo(Model model) {
		Insumo insumo = new Insumo();
		List<String> links = new ArrayList<>();
		
		insumo.setLinks(links);
		
		model.addAttribute("insumo", insumo);
		model.addAttribute("categoriaProductos", this.categoriaProductoService.findAll());
		model.addAttribute("proveedores", this.proveedorService.findAll());
		
		return "agregarInsumoForm";
	}
	
	@PostMapping("/guardarInsumo")
	public String guardarInsumoNuevo(@ModelAttribute Insumo insumo, @RequestParam Long idCategoriaProducto, @RequestParam Long idProveedor) {
		this.insumoService.save(insumo, idCategoriaProducto, idProveedor);
		return "redirect:/insumos/listar";
	}
	
	@GetMapping("actualizarInsumo/{id}")
	public String actualizarInsumo(@PathVariable Long id, Model model) {
		model.addAttribute("insumo", insumoService.findById(id));
		model.addAttribute("categoriaProductos", this.categoriaProductoService.findAll());
		model.addAttribute("proveedores", this.proveedorService.findAll());
		return "actualizarInsumoForm";
	}
	
	@PostMapping("actualizarInsumo/{id}")
	public String actualizarInsumo(@PathVariable Long id, @ModelAttribute Insumo insumo, @RequestParam Long idCategoriaProducto, @RequestParam Long idProveedor) {
		insumoService.actualizarInsumo(id, insumo, idProveedor, idCategoriaProducto);
		return "redirect:/insumos/listar";
	}
	
	@GetMapping("eliminarInsumo/{id}")
	public String eliminarInsumoPorId(@PathVariable Long id) {
		this.insumoService.eliminarPorId(id);
		return "redirect:/insumos/listar";
	}
	
}
