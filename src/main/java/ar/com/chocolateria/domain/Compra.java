package ar.com.chocolateria.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compras")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "precio")
	@Positive(message = "El precio debe ser mayor a 0")
	private Float precio;
	
	@Column(name = "fecha_compra", nullable = true)
	@NotNull(message = "La fecha de compra es obligatoria")
	private LocalDate fechaCompra;
	
	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval true para eliminar los insumosComprados
	private List<InsumoComprado> insumosComprados;
	
}
