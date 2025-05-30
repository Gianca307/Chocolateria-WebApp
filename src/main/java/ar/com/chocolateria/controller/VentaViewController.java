package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.chocolateria.domain.Venta;
import ar.com.chocolateria.service.ProductoService;
import ar.com.chocolateria.service.VentaService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/ventas")
public class VentaViewController {

	private VentaService ventaService;
	private ProductoService productoService;
	
	@GetMapping("/listar")
	public String listarVenta(Model model) {
		model.addAttribute("ventas", this.ventaService.listarVentas());
		return "listaVentas";
	}
	
	@GetMapping("/agregarVenta")
	public String agregarVenta(Model model) {
		model.addAttribute("venta", new Venta());
		model.addAttribute("productos", this.productoService.listarProductos());
		return "agregarVentaForm";
	}
	
	@PostMapping("/guardarVenta")
	public String guardarVenta(@ModelAttribute Venta venta) {
		this.ventaService.guardarVenta(venta);
		return "redirect:/ventas/listar";
	}
	
	@GetMapping("/actualizarVenta/{id}")
	public String modificarVenta(@PathVariable Long id, Model model) {
		model.addAttribute("venta", this.ventaService.obtenerVentaPorId(id));
		model.addAttribute("productos", this.productoService.listarProductos());
		return "actualizarVentaForm";
	}
	
	@PostMapping("/actualizarVenta/{id}")
	public String modificarVenta(@PathVariable Long id, @ModelAttribute Venta venta) {
		this.ventaService.actualizarVenta(venta, id);		
		return "redirect:/ventas/listar";
	}
	
	@GetMapping("/eliminarVenta/{id}")
	public String eliminarVentaPorId(@PathVariable Long id) {
		this.ventaService.eliminarVenta(id);
		return "redirect:/ventas/listar";
	}
}