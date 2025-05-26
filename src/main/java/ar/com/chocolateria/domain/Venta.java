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
@Table(name = "ventas")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_venta", nullable = true)
	@NotNull(message = "La fecha de compra es obligatoria")
	private LocalDate fechaVenta;
	
	@Column(name = "precio", nullable = false)
	@Positive(message = "Precio debe tener un valor positivo")
	private Float precio;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductoVendido> productosVendidos;
}
