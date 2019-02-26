package guille.guillegram.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guille.guillegram.api.model.Destino;

@Repository
@Transactional
public interface DestinoDaoI extends CrudRepository<Destino,Integer>{

	@Query("select d from Destino d where d.usuario.id = :id")
	List<Destino> findDestinosByIdUsuario(@Param("id") int id);
}
