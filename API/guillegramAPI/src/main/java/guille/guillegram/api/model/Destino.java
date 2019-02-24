package guille.guillegram.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Destino {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(nullable = false)
	private String descripcion;

	@Column(nullable = false)
	private String imagen;

	@Column(nullable = false)
	private double latitud;

	@Column(nullable = false)
	private double longitud;

	@ManyToMany
	private Set<Usuario> listaUsuarios;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(Set<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
