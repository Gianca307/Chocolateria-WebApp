package ar.com.chocolateria.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "insumo_producto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InsumoProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message = "Cantidad debe ser mayor a 0")
	private Integer cantidad;
	
	@ManyToOne
	@JoinColumn(name = "insumo_id", referencedColumnName = "id")
	private Insumo insumo;
	
	@ManyToOne
	@JoinColumn(name = "producto_id", referencedColumnName = "id")
	private Producto producto;
}
