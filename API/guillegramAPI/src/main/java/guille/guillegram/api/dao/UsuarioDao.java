package guille.guillegram.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guille.guillegram.api.model.Usuario;

@Repository
@Transactional
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

}
