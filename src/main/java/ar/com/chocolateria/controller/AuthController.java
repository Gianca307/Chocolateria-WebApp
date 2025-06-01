package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.chocolateria.domain.Usuario;
import ar.com.chocolateria.service.CustomUserDetailsService;
import ar.com.chocolateria.service.ProductoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@AllArgsConstructor
@Slf4j
public class AuthController {

	private final CustomUserDetailsService customUserDetailsService;
	private final ProductoService productoService;
	
	@GetMapping({"/" , "/home"})
	public String redireccionarPaginaPrincipal(Model model) {
		model.addAttribute("productosHome", this.productoService.listarProductos());		
		return "home";
	}
	
	@GetMapping({"/home/{categoria}"})
	public String redireccionarPagina(Model model,  @PathVariable String categoria) {
		model.addAttribute("productosHome", this.productoService.listarProductosPorCategoria(categoria));
		model.addAttribute("categoriaProducto", categoria);
				
		return "home";
	}
	
	@GetMapping("/carrito")
	public String mostrarCarrito() {
		return "carrito";
	}
	
	@GetMapping("/buscar")
    public String buscar(@RequestParam("q") String consulta, Model model) {
        model.addAttribute("productosHome", productoService.buscar(consulta));
        return "home"; // devuelve el nombre del template (ej: resultado-busqueda.html)
    }
	
	@GetMapping("/login")
	public String login () {
		return "login";
	}
	
	@GetMapping("/registro")
	public String mostrarFormularioRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/registro")
	public String registrarUsuario(@ModelAttribute Usuario usuario) {
		this.customUserDetailsService.guardarUsuario(usuario);
		return "redirect:/login";
	}
	
}
