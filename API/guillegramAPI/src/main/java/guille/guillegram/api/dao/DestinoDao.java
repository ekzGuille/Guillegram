package guille.guillegram.api.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guille.guillegram.api.model.Destino;

@Repository
@Transactional
public interface DestinoDao extends CrudRepository<Destino,Integer>{

}
