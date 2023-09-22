package com.adas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adas.models.AutoresModel;

@Service
public interface IAutorService {
	
	List<AutoresModel> findAll();
	AutoresModel findById(Integer id);
	AutoresModel findByCorreo(String correo);
	boolean existsAutor(String correo);
	
	AutoresModel save(AutoresModel autor);
	
	void deleteAutor(Integer id);
}
