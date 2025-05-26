package ar.com.chocolateria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank(message = "El nombre no puede quedar vacío.")
	@Column(name= "nombre")
	private String nombre;
	
	@NotBlank(message = "El apellido no puede quedar vacío.")
	@Column(name= "apellido")
	private String apellido;
	
	@NotBlank(message = "El usuario no puede quedar vacío.")
	@Column(name= "username")
	private String username;
	
	@NotBlank(message = "Contraseña no puede quedar vacía.")
	@Column(name= "contrasena")
	private String contrasena;
	
	@Enumerated(EnumType.STRING)
	private Rol rol;
	
}