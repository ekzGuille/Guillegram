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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario", indexes = { @Index(columnList = "id_usuario", name = "indexIdUsr") })
public class Usuario {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@JsonIgnore
	@NotNull
	@OneToMany(mappedBy = "usuario")
	private List<Destino> usuarios;

	@JsonIgnore
	@NotNull
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
