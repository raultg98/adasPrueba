package com.adas.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adas.models.ActividadesModel;

public interface IActividadesDAO extends CrudRepository<ActividadesModel, Integer>{

	Optional<ActividadesModel> findById(Integer id);
	ActividadesModel findByNombre(String nombre);
	List<ActividadesModel> findByOrderByFechaAsc();
	
	ActividadesModel save(ActividadesModel actividad);
}
