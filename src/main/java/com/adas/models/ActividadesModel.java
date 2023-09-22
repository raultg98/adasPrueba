package com.adas.models;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "actividades")
public class ActividadesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "ubicacion")
	private String ubicacion;
	
	@Column(name = "usuarios")
	private int usuarios;
	
	@Column(name = "voluntarios")
	private int voluntarios;

	
	/******* GETTERS Y SETTERS *******/
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(int usuarios) {
		this.usuarios = usuarios;
	}

	public int getVoluntarios() {
		return voluntarios;
	}

	public void setVoluntarios(int voluntarios) {
		this.voluntarios = voluntarios;
	}
}
