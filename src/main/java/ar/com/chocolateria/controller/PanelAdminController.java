package ar.com.chocolateria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.chocolateria.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/panels")
public class PanelAdminController {

	private CustomUserDetailsService customDetailsService;
	
	@GetMapping("/gestorRoles")
	public String gestorRoles (Model model) {
		model.addAttribute("usuarios", this.customDetailsService.listarUsuariosRegistrados());
		return "gestorRoles";
	}
	
	@GetMapping("/actualizarRolUsuario/{id}")
	public String mostrarFormularioActualizarUsuario(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", this.customDetailsService.obtenerUsuarioPorId(id));
		return "actualizarRolUsuarioForm";
	}
	
	@PostMapping("/actualizarRolUsuario/{id}")
	public String actualizarRolUsuario (@PathVariable Long id, @RequestParam(required = false) String rol) {
		
		this.customDetailsService.actualizarRolUsuario(id, rol);
		
		return "redirect:/panels/gestorRoles";
	}
	
	@GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        this.customDetailsService.eliminarUsuario(id);
        return "redirect:/panels/gestorRoles";
    }
	
}
