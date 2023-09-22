package com.adas.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adas.models.ActividadesModel;

@Service
public interface IActividadService {

	List<ActividadesModel> findAll();
	ActividadesModel findById(Integer id);
	ActividadesModel findByNombre(String nombre);
//	List<ActividadesModel> findOneActividadPerYear();
	List<ActividadesModel> orderByFecha();
	
	void save(ActividadesModel actividad);
	void deleteActividad(Integer id);
}
