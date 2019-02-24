package guille.guillegram.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario", indexes = { @Index(columnList = "id_usuario", name = "indexIdUsr") })
public class Usuario {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private int id;

	@NotNull
	@Column(nullable = false)
	private String nombre;

	@NotNull
	@Column(nullable = false)
	private String correo;

	@NotNull
	@Column(name = "nombre_usuario", nullable = false)
	private String nombreUsuario;

	@NotNull
	@Column(nullable = false)
	private String contrasena;

	@NotNull
	@OneToMany(mappedBy = "usuario")
	private List<Destino> usuarios;

	@NotNull
	@ManyToMany
	private List<Destino> destinosFav;

}
