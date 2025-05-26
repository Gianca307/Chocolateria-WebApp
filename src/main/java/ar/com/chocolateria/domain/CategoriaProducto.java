package ar.com.chocolateria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "categorias_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Categoría no puede estar vacía")
	@Size(max = 50, message = "Categoria no puede tener más de 50 caracteres")
	@Column(name = "categoria", length = 50)
	private String categoria;
	
}
