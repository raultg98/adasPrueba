package com.adas.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adas.DAO.IAutoresDAO;
import com.adas.models.AutoresModel;
import com.adas.services.IAutorService;

@Service
public class AutorServiceImpl implements IAutorService{

	@Autowired
	private IAutoresDAO autoresDAO;
	
	@Override
	public List<AutoresModel> findAll() {
		return (List<AutoresModel>) this.autoresDAO.findAll();
	}

	@Override
	public AutoresModel findById(Integer id) {
		Optional<AutoresModel> optional = this.autoresDAO.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}

	@Override
	public AutoresModel save(AutoresModel autor) {
		return this.autoresDAO.save(autor);
	}

	@Override
	public void deleteAutor(Integer id) {
		this.autoresDAO.deleteById(id);
	}

	@Override
	public boolean existsAutor(String correo) {
		List<AutoresModel> autores = findAll();
		
		for(AutoresModel autor : autores) {
			if(autor.getCorreo().equals(correo)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public AutoresModel findByCorreo(String correo) {
		List<AutoresModel> autores = findAll();
		
		for(AutoresModel autor : autores) {
			if(autor.getCorreo().equals(correo)) {
				return autor;
			}
		}
		
		return null;
	}

}
