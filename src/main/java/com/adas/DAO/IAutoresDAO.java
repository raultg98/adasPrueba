package com.adas.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.adas.models.AutoresModel;

public interface IAutoresDAO extends CrudRepository<AutoresModel, Integer>{

	Optional<AutoresModel> findById(Integer id);
	List<AutoresModel> findAll();

	AutoresModel save(AutoresModel autor);
}
