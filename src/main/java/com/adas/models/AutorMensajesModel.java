package com.adas.models;

public class AutorMensajesModel {

	private Integer id;
	private String nombre;
	private String correo;
	private Integer mensajes;
	
	/******* CONSTRUCTORES *******/
	public AutorMensajesModel() {
		
	}
	
	public AutorMensajesModel(AutoresModel autor, int numeroMensajes) {
		this.id = autor.getId();
		this.nombre = autor.getNombre();
		this.correo = autor.getCorreo();
		this.mensajes = numeroMensajes;
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getMensajes() {
		return mensajes;
	}
	public void setMensajes(Integer mensajes) {
		this.mensajes = mensajes;
	}
	
	
}
