package ar.com.chocolateria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "valor_agregado")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ValorAgregado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "La descripcion no puede quedar vacía.")
	@Column(name = "descripcion")
	private String descripcion;
	
	@Positive(message = "El porcentaje debe tener valor positivo y mayor a 0.")
	@Column(name = "porcentaje")
	private Float porcentaje;
}