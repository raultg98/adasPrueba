package com.adas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adas.models.MensajesModel;

@Service
public interface IMensajeService {

	List<MensajesModel> findAll();
	MensajesModel findById(Integer id);
	List<MensajesModel> findAllMensajesByIdNoticia(Integer id);
	List<MensajesModel> findAllMensajesByAutor(Integer id); 
	
	
	MensajesModel save(MensajesModel mensaje);
	
	void deleteMensaje(Integer id);

}
