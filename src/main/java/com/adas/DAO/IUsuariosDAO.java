package com.adas.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.adas.models.UsuariosModel;

public interface IUsuariosDAO extends CrudRepository<UsuariosModel, Integer>{

	Optional<UsuariosModel> findById(Integer id);
	UsuariosModel findByCorreo(String correo);
	String findPasswordById(Integer id);
	
	UsuariosModel save(UsuariosModel usuario);
}
