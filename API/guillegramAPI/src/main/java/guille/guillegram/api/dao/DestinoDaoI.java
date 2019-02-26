package guille.guillegram.api.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import guille.guillegram.api.model.Destino;

@Repository
@Transactional
public interface DestinoDaoI extends CrudRepository<Destino,Integer>{

//	@Query("select d from Destino d where d.usuario.id = :id")
	@Query("select d from Destino d inner join d.usuariosFav u where u.id = :id")
	List<Destino> findDestinosFavByIdUsuario(@Param("id") int id);
	
	
	@Query("select d from Destino d inner join d.usuario u where u.id = :id")
	List<Destino> findDestinosPubByIdUsuario(@Param("id") int id);
	
	
	//TODO (Borrar un destino de un usuario dado la id del usuario y la id del destino)
	
//	@Query("delete from Usuario u  where u.id:idU and u.destinosFav.id = :idD")
	@Modifying
//	@Query("delete us.destinosFav from Usuario us where :idD in (select d.id from Destino d inner join d.usuariosFav u where u.id = :idU)")
//	@Query("delete d from Usuario u inner join u.destinosFav d where d.id = :idD and u.id = :idU")
	void deleteDestinoById(@Param("idU") int idU, @Param("idD") int idD);
}
