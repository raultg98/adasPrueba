package com.adas.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class UsuariosModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
//	@Column(name = "apellidos")
//	private String apellidos;
	
	@Column(name = "correo")
	private String correo;
	
//	@Column(name = "username")
//	private String username;
	
	@Column(name = "contrasenia")
	private String contrasenia;
	
	@Column(name="cargo")
	@Enumerated(value = EnumType.STRING)
	private Cargos cargo;
	
	@Column(name = "admin")
	private boolean admin;
	
	
	/******* GETTERS Y SETTERS *******/
	public Integer getId() {
		return id;
	}

//	public String getApellidos() {
//		return apellidos;
//	}
//
//	public void setApellidos(String apellidos) {
//		this.apellidos = apellidos;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}

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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
