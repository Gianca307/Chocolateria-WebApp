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

import ar.com.chocolateria.domain.Compra;
import ar.com.chocolateria.domain.InsumoComprado;
import ar.com.chocolateria.service.CompraService;
import ar.com.chocolateria.service.InsumoService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/compras")
public class CompraViewController {

	private CompraService compraService;
	private InsumoService insumoService;
	
	@GetMapping("/listar")
	public String listarCompra(Model model) {
		model.addAttribute("compras", this.compraService.listarCompras());
		return "listaCompras";
	}
	
	@GetMapping("/agregarCompra")
	public String agregarCompra(Model model) {
		
		List<InsumoComprado> insumosComprados = new ArrayList<>();
		Compra compra = new Compra();
		compra.setInsumosComprados(insumosComprados);
		
		model.addAttribute("compra", compra);
		model.addAttribute("insumos", insumoService.findAll());
		
		return "agregarCompraForm";
	}
	
	@PostMapping("/guardarCompra")
	public String guardarCompra(@ModelAttribute Compra compra) {
		this.compraService.guardarCompra(compra);
		return "redirect:/compras/listar";
	}
	
	@GetMapping("/actualizarCompra/{id}")
	public String modificarCompra(@PathVariable Long id, Model model) {
		
		model.addAttribute("compra", this.compraService.obtenerCompraPorId(id));
		model.addAttribute("insumos", insumoService.findAll());
		
		return "actualizarCompraForm";
	}
	
	@PostMapping("/actualizarCompra/{id}")
	public String actualizarCompra(@PathVariable Long id, @ModelAttribute Compra compra) {
		this.compraService.actualizarCompra(id, compra);
		return "redirect:/compras/listar";
	}
	
	@GetMapping("/eliminarCompra/{id}")
	public String eliminarCompraPorId(@PathVariable Long id) {
		this.compraService.eliminarCompraPorId(id);
		return "redirect:/compras/listar";
	}
	
}
