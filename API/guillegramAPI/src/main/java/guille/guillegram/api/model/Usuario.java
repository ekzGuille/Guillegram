package guille.guillegram.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Usuario {

	@Id
	@NotNull
	@Column(name = "id_usuario")
	//Genera una PK indexada. SOLO MYSQL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@JsonIgnore
	// mappedBy es el atributo de la otra clase con el que se enlaza
	@OneToMany(mappedBy = "usuario")
	private List<Destino> usuarios;

	//Ignorar esta propiedad (para evitar bucle infinito)
	@JsonIgnoreProperties("usuario")
	@ManyToMany
	private List<Destino> destinosFav;

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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public List<Destino> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Destino> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Destino> getDestinosFav() {
		return destinosFav;
	}

	public void setDestinosFav(List<Destino> destinosFav) {
		this.destinosFav = destinosFav;
	}

}
