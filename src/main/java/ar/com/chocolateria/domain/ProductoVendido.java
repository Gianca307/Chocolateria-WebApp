package ar.com.chocolateria.domain;

import jakarta.persistence.Column;
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
@Table(name = "productos_vendidos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoVendido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cantidad", nullable = false)
	@Positive(message = "Cantidad debe tener un valor positivo")
	private Integer cantidad;
	
	@Column(name = "precio", nullable = false)
	@Positive(message = "Precio debe tener un valor positivo")
	private Float precio;
	
	@ManyToOne
	@JoinColumn(name = "producto_id", referencedColumnName = "id")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "venta_id", referencedColumnName = "id")
	private Venta venta;
}
