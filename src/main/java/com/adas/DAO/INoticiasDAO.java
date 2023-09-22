package com.adas.DAO;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adas.models.NoticiasModel;

public interface INoticiasDAO extends CrudRepository<NoticiasModel, Integer>{

	Optional<NoticiasModel> findById(Integer id);
	NoticiasModel findByUsuarioId(Integer id);
	
	NoticiasModel save(NoticiasModel noticia);
}
