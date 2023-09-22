package com.adas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adas.models.UsuariosModel;

@Service
public interface IUsuarioService {

	List<UsuariosModel> findAll();
	UsuariosModel findById(Integer id);
	String findPasswordById(Integer id);
	
	void save(UsuariosModel usuario);
	void deleteUsuario(Integer id);
}
