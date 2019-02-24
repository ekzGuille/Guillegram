package guille.guillegram.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guille.guillegram.api.model.Usuario;

@Repository
@Transactional
public interface UsuarioDaoI extends CrudRepository<Usuario, Integer> {

	@Query("select u from Usuario u where (u.correo like :username_mail or u.nombreUsuario like :username_mail) and u.contrasena like :contrasena")
	Usuario findUserbyUsernameMailContrasena(@Param("username_mail") String username_mail,
			@Param("contrasena") String contrasena);

}
