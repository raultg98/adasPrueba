package com.adas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class AutoresModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_autor")
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "correo")
	private String correo;
	
//	private int mensajes;

	/******* CONSTRUCTORES *******/
	public AutoresModel() {
		
	}
	
	public AutoresModel(String nombre, String correo) {
		this.setNombre(nombre);
		this.setCorreo(correo);
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
	
//	public void setMensajes() {
//		this.mensajes++;
//	}
//	
//	public int getMensajes() {
//		return this.mensajes;
//	}
}
