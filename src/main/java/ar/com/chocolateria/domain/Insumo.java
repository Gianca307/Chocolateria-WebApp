package ar.com.chocolateria.domain;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "insumos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Insumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descripcion")
	@NotBlank(message = "Descripcion no puede estar vacío")
	private String descripcion;
	
	@Column(name = "cantidad")
	private Integer cantidad;
	
	@Column(name = "unidad")
	@NotBlank(message = "Unidad no puede quedar vacío")
	private String unidad;
	
	@Column(name = "costo_insumo")
	@Positive(message = "Costo no puede ser un número negativo y debe ser mayor a 0")
	private Float costoInsumo;
	
	@Column(name = "stock")	
	private Integer stock;
	
	@Column(name = "img", nullable = true)
	private String img;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private CategoriaProducto categoriaProducto;
	
	@ManyToOne
	@JoinColumn(name = "proveedor_id", referencedColumnName = "id") // esto esta mal, es una relacion de muchos a uno
	private Proveedor proveedor;
	
	@ElementCollection
	@CollectionTable(name = "insumo_links", joinColumns = @JoinColumn(name = "insumo_id"))
	@Column(name = "link")
	private List<String> links;
}
