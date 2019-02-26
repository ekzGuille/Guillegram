package guille.guillegram.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guille.guillegram.api.dao.DestinoDaoI;
import guille.guillegram.api.dao.UsuarioDaoI;
import guille.guillegram.api.model.Destino;
import guille.guillegram.api.model.Usuario;

@Controller
@RequestMapping(path = "/api")
public class AppController {

	@Autowired
	private UsuarioDaoI usr;

	@Autowired
	private DestinoDaoI des;

	@GetMapping(value = "/")
	public @ResponseBody String welcome() {
		return "" + "<div 'style: text-align:center;'>" + "<h1>Guillegram api home</h1>" + "<ul>"
				+ "<li><a href='usuarios/list'>Listar Usuarios</a></li>"
				+ "<li><a href='destinos/list'>Listar Destinos</a></li>" + "</ul>" + "</div>";
	}

	/*
	 * USUARIOS
	 */

	@GetMapping(value = "usuarios/list")
	public @ResponseBody Iterable<Usuario> listUsr() {
		return usr.findAll();
	}

	@GetMapping(value = "usuarios/login/{username_mail}/{contrasena}")
	public @ResponseBody Usuario login(@PathVariable("username_mail") String username_mail,
			@PathVariable("contrasena") String contrasena) {
		return usr.findUserbyUsernameMailContrasena(username_mail, contrasena);
	}

	@PostMapping(value = "usuarios/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Usuario register(@RequestBody Usuario u) {
		usr.save(u);
		Optional<Usuario> registered = usr.findById(u.getId());
		return registered.isPresent() ? registered.get() : null;
	}

	@PostMapping(value = "usuarios/delete")
	public void delUsr(@RequestBody Usuario u) {
		usr.delete(u);
	}

	@PutMapping(value = "usuarios/update")
	public void updUsr(@RequestBody Usuario u) {
		usr.save(u);
	}

	@GetMapping(value = "usuarios/get/{id}")
	public @ResponseBody Usuario getUsr(@PathVariable("id") int id) {
		Optional<Usuario> found = usr.findById(id);
		return found.isPresent() ? found.get() : null;
	}

	@PutMapping
	public void addFavorito(@RequestBody Usuario u, @RequestBody Destino d) {
//		u.setDestinosFav(new HashSet<>()v);;
	}

	/*
	 * DESTINOS
	 */

	@GetMapping(value = "destinos/list")
	public @ResponseBody Iterable<Destino> listDes() {
		return des.findAll();
	}

	@PostMapping(value = "destinos/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUDes(@RequestBody Destino d) {
		des.save(d);
	}

	@PostMapping(value = "destinos/delete")
	public void delUDes(@RequestBody Destino d) {
		des.delete(d);
	}

	@PutMapping(value = "destinos/update")
	public void updUDes(@RequestBody Destino d) {
		des.save(d);
	}
	
	@GetMapping(value="destinos/usuario/{id}")
	public @ResponseBody Iterable<Destino> getDestinos(@PathVariable("id") int id) {
		return des.findDestinosByIdUsuario(id);
	}
}
