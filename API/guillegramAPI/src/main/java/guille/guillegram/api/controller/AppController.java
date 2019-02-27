package guille.guillegram.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

//	@PostMapping(value = "u/a")
//	public @ResponseBody Usuario u(@RequestBody Usuario u) {
//		usr.save(u);
//		Optional<Usuario> found = usr.findById(u.getId());
//		return found.isPresent() ? found.get() : null;
//	}
//
//	@PostMapping(value = "u/d")
//	public @ResponseBody Usuario us(@RequestBody Usuario u) {
//		usr.delete(u);
//		Optional<Usuario> found = usr.findById(u.getId());
//		return found.isPresent() ? found.get() : null;
//	}

	@PostMapping(value = "destinos/usuario/delete/{idU}/{idD}")
	public @ResponseBody Usuario deleteDestinos(@PathVariable("idU") int idU, @PathVariable("idD") int idD) {
		// TODO
//		des.deleteDestinoById(idU, idD);
		return usr.findById(idU).get();
	}

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

	@GetMapping(value = "destinos/usuario/favoritos/{id}")
	public @ResponseBody Iterable<Destino> getDestinosFav(@PathVariable("id") int id) {
		return des.findDestinosFavByIdUsuario(id);
	}

	@GetMapping(value = "destinos/usuario/publicados/{id}")
	public @ResponseBody Iterable<Destino> getDestinosPub(@PathVariable("id") int id) {
		return des.findDestinosPubByIdUsuario(id);
	}

	@PostMapping(value = "destinos/usuario/favoritos/update/{idU}")
	public @ResponseBody Integer updateFavoritosDestinos(@PathVariable("idU") int idU, @RequestBody Destino d) {

		Integer updelete = new Integer(0);
		
		Usuario usuario = usr.findById(idU).get();
		Destino destino = des.findById(d.getId()).get();

		List<Destino> l = usuario.getDestinosFav();
		if (l.contains(destino)) {
			updelete = -1;
			usuario.getDestinosFav().remove(destino);
		} else {
			updelete = 1;
			usuario.getDestinosFav().add(destino);
		}
		usr.save(usuario);
		return updelete;
	}
}
