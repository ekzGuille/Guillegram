package guille.guillegram.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import guille.guillegram.api.dao.DestinoDao;
import guille.guillegram.api.dao.UsuarioDao;
import guille.guillegram.api.model.Destino;
import guille.guillegram.api.model.Usuario;

@Controller
@RequestMapping(path = "/api")
public class AppController {

	@Autowired
	private UsuarioDao usr;

	@Autowired
	private DestinoDao des;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody String welcome() {
		return "<h1>Guillegram api home</h1>";
	}

	@RequestMapping(value = "usuarios/list", method = RequestMethod.GET)
	public @ResponseBody Iterable<Usuario> listUsr() {
		return usr.findAll();
	}

	@RequestMapping(value = "usuarios/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUsr(@RequestBody Usuario u) {
		usr.save(u);
	}

	@RequestMapping(value = "usuarios/delete", method = RequestMethod.POST)
	public void delUsr(@RequestBody Usuario u) {
		usr.delete(u);
	}

	@RequestMapping(value = "usuarios/update", method = RequestMethod.PUT)
	public void updUsr(@RequestBody Usuario u) {
		usr.save(u);
	}

	@RequestMapping(value = "destinos/list", method = RequestMethod.GET)
	public @ResponseBody Iterable<Destino> listDes() {
		return des.findAll();
	}

	@RequestMapping(value = "destinos/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addUDes(@RequestBody Destino d) {
		des.save(d);
	}

	@RequestMapping(value = "destinos/delete", method = RequestMethod.POST)
	public void delUDes(@RequestBody Destino d) {
		des.delete(d);
	}

	@RequestMapping(value = "destinos/update", method = RequestMethod.PUT)
	public void updUDes(@RequestBody Destino d) {
		des.save(d);
	}
}
