package ar.com.chocolateria.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descripcion", nullable = false)
	@NotBlank(message = "La descripción no puede quedar en blanco")
	private String descripcion;
	
	@Column(name = "costo_base")
	@Positive(message = "Costo base debe tener un valor positivo")
	private Float costoBase;
	
	@Column(name = "precio")
	@Positive(message = "Precio debe tener un valor positivo")
	private Float precio;
	
	@Column(name = "img_url", nullable = false)
	@NotNull(message = "Imagen no debe quedar nulo")
	private String imgUrl;
	
	@Column(name = "disponible")
	@NotNull(message = "Disponible no puede quedar nulo")
	private Boolean disponible;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private CategoriaProductoOferta categoriaProductoOferta;
	
	@OneToMany(mappedBy = "producto", cascade = jakarta.persistence.CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
	private List<InsumoProducto> insumosProductos;
}
