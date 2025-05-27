package ar.com.chocolateria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.chocolateria.domain.Rol;
import ar.com.chocolateria.domain.Usuario;
import ar.com.chocolateria.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByUsername(username);
		
		if (usuario == null) {
            throw new UsernameNotFoundException("loadUserByUsername: Usuario no encontrado: "+ username); //Ser lo más genérico posible para no darle tanta insformación al atacante
        }
		
		return User.withUsername(usuario.getUsername())
                .password(usuario.getContrasena())
                .authorities(List.of(new SimpleGrantedAuthority(usuario.getRol().name())))
                .build();
		
	} //esta clase nos permite interactuar con el usuario de nuestro modelo y el usuario de la sesión
	
	public Usuario guardarUsuario(Usuario usuario) {

        Usuario usuarioExistente = usuarioRepository.findByUsername(usuario.getUsername());

        if (usuarioExistente != null) {
            throw new DataIntegrityViolationException("El usuario ya se encuentra registrado!");
        }

        usuario.setRol(Rol.ROL_LECTURA);
        usuario.setContrasena(passwordEncoder().encode(usuario.getContrasena())); //codifica la contraseña copn bcrypt

        return usuarioRepository.save(usuario);
    }
	
	@Transactional
	public void eliminarUsuario(Long id) {
	    Optional<Usuario> usuarioActual = usuarioRepository.findById(id);
	    String usernameActual = SecurityContextHolder.getContext().getAuthentication().getName();

	    if (usuarioActual.isPresent()) {
	        //Sesión activa: No permitimos eliminar el usuario que tiene la sesión actual abierta
	        if (usernameActual.equals(usuarioActual.get().getUsername())) {
	            throw new IllegalArgumentException("No se puede eliminar el usuario actualmente autenticado");
	        }      
        }
        usuarioRepository.deleteById(id);
    }
	
	public List<Usuario> listarUsuariosRegistrados() {
	    return usuarioRepository.findAll();
	}
	
	public Usuario obtenerUsuarioPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(()
				-> new UsernameNotFoundException("No se encontró el usuario con id: "+id));
    }
	
	public void actualizarRolUsuario(Long id, String nuevoRol) {
		Usuario usuario = obtenerUsuarioPorId(id);

		if (usuario == null) {
			throw new UsernameNotFoundException("actualizarRolUsuario: Usuario no encontrado");
		}

		usuario.setRol(Rol.valueOf(nuevoRol));

		usuarioRepository.save(usuario);
    }

	
	public PasswordEncoder passwordEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
