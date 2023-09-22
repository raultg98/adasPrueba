package com.adas.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.adas.models.MensajesModel;

public interface IMensajesDAO extends CrudRepository<MensajesModel, Integer>{

	Optional<MensajesModel> findById(Integer id);
	List<MensajesModel> findAll();
//	List<MensajesModel> findMensajesByIdNoticia(Integer id);
	MensajesModel save(MensajesModel mensaje);
}
