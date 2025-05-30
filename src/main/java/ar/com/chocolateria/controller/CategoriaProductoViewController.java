package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.chocolateria.domain.CategoriaProducto;
import ar.com.chocolateria.service.CategoriaProductoService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/categoriaProductos")
public class CategoriaProductoViewController {
	private final CategoriaProductoService categoriaProductoService;
	
	@GetMapping("/listarCategoriaProductos")
	public String listarCategoriaProductos (Model model) {
		model.addAttribute("categoriaProductos", categoriaProductoService.findAll());
		return "listaCategoriaProductos";
	}
	
	@GetMapping("/actualizarCategoriaProducto/{id}")
	public String actualizarCategoriaProducto (@PathVariable Long id, Model model) {
		model.addAttribute("categoriaProducto" ,categoriaProductoService.findById(id));
		return "actualizarCategoriaProductoForm";
	}	
	
	@GetMapping("/eliminarCategoriaProducto/{id}")
	public String eliminarCategoriaProducto(@PathVariable Long id) {
		categoriaProductoService.eliminarPorId(id);
		return "redirect:/categoriaProductos/listarCategoriaProductos";
	}
	
	@PostMapping("/actualizarCategoriaProducto/{id}")
	public String actualizarCategoriaProducto(@PathVariable Long id, @ModelAttribute CategoriaProducto categoriaProducto) {
		categoriaProductoService.actualizarCategoriaProducto(id, categoriaProducto);
		return "redirect:/categoriaProductos/listarCategoriaProductos";
	}
	
	@GetMapping("/agregarCategoriaProducto")
	public String agregarCategoriaProducto(Model model) {
		model.addAttribute("categoriaProducto", new CategoriaProducto());
		return "agregarCategoriaProductoForm";
	}
	
	@PostMapping("/agregarCategoriaProducto")
	public String agregarCategoriaProducto(@ModelAttribute CategoriaProducto categoriaProducto) {
		categoriaProductoService.save(categoriaProducto);
		return "redirect:/categoriaProductos/listarCategoriaProductos";
	}
	
}
