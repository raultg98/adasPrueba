package com.adas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adas.models.NoticiasModel;
import com.adas.models.UsuariosModel;

@Service
public interface INoticiaService {
	
	List<NoticiasModel> findAll();
	NoticiasModel findById(Integer id);
	List<NoticiasModel> findByUsuarioId(Integer id);
	Integer noticiasActuales();
	
	NoticiasModel save(NoticiasModel noticia);
	
	void delete(Integer id);
	
}
