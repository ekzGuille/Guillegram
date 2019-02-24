package guille.guillegram.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "destino", indexes = { @Index(columnList = "id_destino", name = "indexIdDes") })
public class Destino {

	@Id
	@Column(name = "id_destino")
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Column(nullable = false)
	private String nombre;

	@NotNull
	@Column(nullable = false)
	private String descripcion;

	@NotNull
	@Column(nullable = false)
	private String imagen;

	@NotNull
	@Column(nullable = false)
	private double latitud;

	@NotNull
	@Column(nullable = false)
	private double longitud;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@NotNull
	@ManyToMany(mappedBy = "destinosFav")
	private List<Usuario> usuariosFav;

}
