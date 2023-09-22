package com.adas.models;

import java.sql.Date;

public class ActividadesImagenModel {

	private Integer id;
	private String nombre;
	private Date fecha;
	private String ubicacion;
	private int usuarios;
	private int voluntarios;
	private String foto;
	
	/******* CONSTRUCTOR *******/
	public ActividadesImagenModel() {
		
	}
	
	public ActividadesImagenModel(ActividadesModel actividad, String foto) {
		this.id = actividad.getId();
		this.nombre = actividad.getNombre();
		this.fecha = actividad.getFecha();
		this.ubicacion = actividad.getUbicacion();
		this.usuarios = actividad.getUsuarios();
		this.voluntarios = actividad.getVoluntarios();
		this.foto = foto;
	}
	
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
	
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}
}
