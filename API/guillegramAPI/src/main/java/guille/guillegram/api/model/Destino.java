package guille.guillegram.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Destino {

	@Id
	@NotNull
	@Column(name = "id_destino")
	//Genera una PK indexada. SOLO MYSQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	//Hay que indicar con qué columna se quiere unir
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	//Ignorar esta propiedad (para evitar bucle infinito)
	@JsonIgnoreProperties("destinosFav")
	@ManyToMany(mappedBy = "destinosFav")
	private List<Usuario> usuariosFav;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuariosFav() {
		return usuariosFav;
	}

	public void setUsuariosFav(List<Usuario> usuariosFav) {
		this.usuariosFav = usuariosFav;
	}

}
