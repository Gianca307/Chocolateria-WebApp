package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.chocolateria.domain.CategoriaProductoOferta;
import ar.com.chocolateria.service.CategoriaProductoOfertaService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/categoriaProductoOferta")
public class CategoriaProductoOfertaViewController {
	private final CategoriaProductoOfertaService categoriaProductoOfertaService;
	
	@GetMapping("/listar")
	public String listarCategoriaProductosOferta (Model model) {
		model.addAttribute("categoriaProductosOferta", this.categoriaProductoOfertaService.findAll());
		return "listaCategoriaProductosOferta";
	}
	
	@GetMapping("/actualizarCategoriaProductoOferta/{id}")
	public String actualizarCategoriaProductoOferta (@PathVariable Long id, Model model) {
		model.addAttribute("categoriaProductoOferta", this.categoriaProductoOfertaService.findById(id));
		return "actualizarCategoriaProductoOfertaForm";
	}
	
	@GetMapping("/eliminarCategoriaProductoOferta/{id}")
	public String eliminarCategoriaProductoOferta(@PathVariable Long id) {
		this.categoriaProductoOfertaService.eliminarPorId(id);
		return "redirect:/categoriaProductoOferta/listar";
	}
	
	@PostMapping("/actualizarCategoriaProductoOferta/{id}")
	public String actualizarCategoriaProductoOferta(@PathVariable Long id, @ModelAttribute CategoriaProductoOferta categoriaProductoOferta) {
		this.categoriaProductoOfertaService.actualizarCategoriaProductoOferta(id, categoriaProductoOferta);
		return "redirect:/categoriaProductoOferta/listar";
	}
	
	@GetMapping("/agregarCategoriaProductoOferta")
	public String agregarCategoriaProductoOferta(Model model) {
		model.addAttribute("categoriaProductoOferta", new CategoriaProductoOferta());
		return "agregarCategoriaProductoOfertaForm";
	}
	
	@PostMapping("/agregarCategoriaProductoOferta")
	public String agregarCategoriaProductoOferta(@ModelAttribute CategoriaProductoOferta categoriaProductoOferta) {
		this.categoriaProductoOfertaService.save(categoriaProductoOferta);
		return "redirect:/categoriaProductoOferta/listar";
	}
	
}
