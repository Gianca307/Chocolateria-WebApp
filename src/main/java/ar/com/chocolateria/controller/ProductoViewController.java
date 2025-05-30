package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.chocolateria.domain.Producto;
import ar.com.chocolateria.service.CategoriaProductoOfertaService;
import ar.com.chocolateria.service.InsumoService;
import ar.com.chocolateria.service.ProductoService;
import ar.com.chocolateria.service.ValorAgregadoService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/productos")
public class ProductoViewController {

	private ProductoService productoService;
	private InsumoService insumoService;
	private CategoriaProductoOfertaService categoriaProductoOfertaService;
	private ValorAgregadoService valorAgregadoService;
	
	@GetMapping("/listar")
	public String listarProductos (Model model) {
		model.addAttribute("productos", this.productoService.listarProductos());		
		return "listaProductos";
	}
	
	@GetMapping("/agregarProducto")
	public String agregarProducto (Model model) {
		
		model.addAttribute("producto", new Producto());
		model.addAttribute("insumos", this.insumoService.findAll());
		model.addAttribute("categoriaProductosOfertas", this.categoriaProductoOfertaService.findAll());
		model.addAttribute("costoAdic", valorAgregadoService.sumarPorcentajesValorAgregado());
		
		return "agregarProductoForm";
	}
	
	@PostMapping("/guardarProducto")
	public String guardarProducto(@ModelAttribute Producto producto, @RequestParam Long idCategoriaProductoOferta) {
		this.productoService.guardarProducto(producto, idCategoriaProductoOferta);		
		return "redirect:/productos/listar";
	}
	
	@GetMapping("/modificarProducto/{id}")
	public String actualizarProducto (@PathVariable Long id, Model model) {
		
		model.addAttribute("producto", this.productoService.obtenerProductoPorId(id));
		model.addAttribute("insumos", this.insumoService.findAll());
		model.addAttribute("categoriaProductosOfertas", this.categoriaProductoOfertaService.findAll());
		model.addAttribute("costoAdic", valorAgregadoService.sumarPorcentajesValorAgregado());
		
		return "actualizarProductoForm";
	}	

	@PostMapping("/actualizarProducto/{id}")	
	public String actualizarProducto (@PathVariable Long id, @ModelAttribute Producto producto, @RequestParam Long idCategoriaProductoOferta) {
		this.productoService.actualizarProducto(id, producto, idCategoriaProductoOferta);
		return "redirect:/productos/listar";
	}
	
	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto (@PathVariable Long id) {
		this.productoService.eliminarProductoPorId(id);
		return "redirect:/productos/listar";
	}
}
