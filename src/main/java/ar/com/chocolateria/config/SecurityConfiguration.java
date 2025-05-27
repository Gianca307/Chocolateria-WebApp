package ar.com.chocolateria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

	private final UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.userDetailsService(userDetailsService)// le pasamos el user details service para tener las operaciones de login y demas
			.authorizeHttpRequests(request -> request
					.requestMatchers("/", "home/**", "/buscar", "/carrito").permitAll() // esto es para que cualquier persona pueda acceder a estas direcciones
					.requestMatchers("/js/home.js", "/js/carrito.js").permitAll()
					.requestMatchers(("/panels/gestorRoles")).hasAuthority("ROL_ADMIN")
					.requestMatchers(("/panels/actualizarRolUsuario")).hasAuthority("ROL_ADMIN")
					.requestMatchers(("/valorAgregado/**")).hasAuthority("ROL_ADMIN")
					.requestMatchers(("/registro")).hasAuthority("ROL_ADMIN")
					.anyRequest().authenticated())
			.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer // este es el formulario de login, se recicle
					.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/insumos/listar"))  // pagina luego de logearse
			.logout(logout -> logout
				    .permitAll()
				    .logoutSuccessUrl("/home"))
			.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // solicitudes por el mismo origen

		return http.build();
	}
}
