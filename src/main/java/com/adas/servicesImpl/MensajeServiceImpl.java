package com.adas.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adas.DAO.IMensajesDAO;
import com.adas.models.MensajesModel;
import com.adas.services.IMensajeService;

import jakarta.transaction.Transactional;

@Service
//@Transactional
public class MensajeServiceImpl implements IMensajeService{

	@Autowired
	private IMensajesDAO mensajeDAO;
	
	@Override
	public List<MensajesModel> findAll() {
		return (List<MensajesModel>) this.mensajeDAO.findAll();
	}

	@Override
	public MensajesModel findById(Integer id) {
		Optional<MensajesModel> optional = this.mensajeDAO.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else return null;
	}

	@Override
	public List<MensajesModel> findAllMensajesByIdNoticia(Integer id) {
		List<MensajesModel> mensajesTodos = findAll();
		List<MensajesModel> mensajesNoticia = new ArrayList<MensajesModel>();
		
		for(MensajesModel mensaje : mensajesTodos) {
			if(mensaje.getNoticiaId().getId() == id) {
				mensajesNoticia.add(mensaje);
			}
		}
		
		if(mensajesNoticia.size() > 0) {
			return mensajesNoticia;
		}else return null;
	}

	@Override
	public MensajesModel save(MensajesModel mensaje) {
		return this.mensajeDAO.save(mensaje);
	}

	@Override
	public void deleteMensaje(Integer id) {
		this.mensajeDAO.deleteById(id);
	}

	@Override
	public List<MensajesModel> findAllMensajesByAutor(Integer id) {
		List<MensajesModel> allMensajes = findAll();
		List<MensajesModel> mensajesAutor = new ArrayList<MensajesModel>();
		
		for(MensajesModel mensaje : allMensajes) {
			if(mensaje.getAutorId().getId() == id) {
				mensajesAutor.add(mensaje);
			}
		}
		
		if(mensajesAutor.size() > 0) {
			return mensajesAutor;
		}else {
			return null;
		}
	}

}
