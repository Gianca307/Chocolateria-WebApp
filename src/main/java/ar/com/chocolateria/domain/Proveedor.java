package ar.com.chocolateria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 2, max = 100, message = "El nombre de la empresa debe tener entre 2 y 100 caracteres")
	@Column(name = "nombre_empresa", length = 100)
	private String nombreEmpresa;
	
	@Column(name = "telefono_contacto", nullable = true)
	private String telefonoContacto;
	
	@Column(name = "nombre_vendedor", nullable = true)
	private String nombreVendedor;
	
	@Email
	@Column(name = "email", nullable = true)
	private String email;
	
	@Column(name = "pagina_web", nullable = true)
	private String paginaWeb;
	
	@Column(name = "hora_atencion", nullable = true)
	private String horaAtencion;
	
	@Column(name = "direccion", nullable = true)
	private String direccion;
	
}