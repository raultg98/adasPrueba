package com.adas.models;

import java.util.ArrayList;
import java.util.List;

public class ActividadesAcordeonModel {
	
	private int anio;
	private List<ActividadesModel> actividades;
	
	/******* CONSTRUCTOR *******/
	public ActividadesAcordeonModel() {
		
	}
	
	public ActividadesAcordeonModel(int anio) {
		this.setAnio(anio);
		this.actividades = new ArrayList<ActividadesModel>();
	}
	
	public ActividadesAcordeonModel(int anio, List<ActividadesModel> actividades) {
		this.anio = anio;
		this.actividades = actividades;
	}

	/******* GETTERS Y SETTERS *******/
	public int getAnio() {
		return anio;
	}
	
	public void setAnio(int anio) {
		this.anio = anio;
	}

	public void setActividades(List<ActividadesModel> actividades) {
		this.actividades = actividades;
	}
	
	public List<ActividadesModel> getActividades(){
		return this.actividades;
	}
	
	public void setActividad(ActividadesModel actividad) {
		this.actividades.add(actividad);
	}
}
