package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.chocolateria.domain.ValorAgregado;
import ar.com.chocolateria.service.ValorAgregadoService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/valorAgregado")
public class ValorAgregadoViewController {

	private final ValorAgregadoService valorAgregadoService;
	
	@GetMapping("/listar")
	public String listarValorAgregado (Model model) {
		model.addAttribute("valoresAgregados", this.valorAgregadoService.findAll());
		return "listaValorAgregado";
	}
	
	@GetMapping("/agregarValorAgregado")
	public String agregarValorAgregado (Model model) {
		model.addAttribute("valorAgregado", new ValorAgregado());
		return "agregarValorAgregadoForm";
	}
	
	@PostMapping("/agregarValorAgregado")
	public String agregarValorAgregado(@ModelAttribute ValorAgregado valorAgregado) {
		this.valorAgregadoService.save(valorAgregado);
		return "redirect:/valorAgregado/listar";
	}
	
	@GetMapping("/actualizarValorAgregado/{id}")
	public String actualizarValorAgregado (@PathVariable Long id, Model model) {
		model.addAttribute("valorAgregado", this.valorAgregadoService.findById(id));
		return "actualizarValorAgregadoForm";
	}
	
	@PostMapping("/actualizarValorAgregado/{id}")
	public String actualizarValorAgregado (@PathVariable Long id, @ModelAttribute ValorAgregado valorAgregado) {
		this.valorAgregadoService.actualizarValorAgregado(id, valorAgregado);
		return "redirect:/valorAgregado/listar";
	}
	
	@GetMapping("/eliminarValorAgregado/{id}")
	public String eliminarValorAgregadoPorId(@PathVariable Long id) {
		this.valorAgregadoService.eliminarValorAgregadoPorId(id);
		return "redirect:/valorAgregado/listar";
	}
	
}
