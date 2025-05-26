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
@Table(name = "insumos_comprados")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InsumoComprado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cantidad")
	@Positive(message = "Cantidad debe ser distinto de 0 y positivo")
	private Integer cantidad;
	
	@Column(name = "precio")
	@Positive(message = "Precio debe ser distinto de 0 y positivo")
	private Float precio;
	
	@ManyToOne
	@JoinColumn(name = "insumo_id", referencedColumnName = "id")
	private Insumo insumo;
	
	@ManyToOne
	@JoinColumn(name = "compra_id", referencedColumnName = "id")
	private Compra compra;
}
