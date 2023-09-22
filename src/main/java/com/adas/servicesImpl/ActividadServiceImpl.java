package com.adas.servicesImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adas.DAO.IActividadesDAO;
import com.adas.models.ActividadesImagenModel;
import com.adas.models.ActividadesModel;
import com.adas.services.IActividadService;

@Service
public class ActividadServiceImpl implements IActividadService{
	
	@Autowired
	private IActividadesDAO actividadesDAO;

	@Override
	public List<ActividadesModel> findAll() {
		return (List<ActividadesModel>) this.actividadesDAO.findAll();
	}

	@Override
	public ActividadesModel findById(Integer id) {
		return this.actividadesDAO.findById(id).get();
	}

	@Override
	public void save(ActividadesModel actividad) {
		this.actividadesDAO.save(actividad);
	}

	@Override
	public void deleteActividad(Integer id) {
		this.actividadesDAO.deleteById(id);
	}

	@Override
	public List<ActividadesModel> orderByFecha() {
		return this.actividadesDAO.findByOrderByFechaAsc();
	}

	@Override
	public ActividadesModel findByNombre(String nombre) {
		return this.actividadesDAO.findByNombre(nombre);
	}

//	@Override
//	public List<ActividadesImagenModel> findOneActividadPerYear() {
//		List<ActividadesModel> allActividades = (List<ActividadesModel>) this.actividadesDAO.findAll();
//		List<ActividadesImagenModel> actividadPorAnio = new ArrayList<ActividadesImagenModel>();
//		
//		int contador = 0;
//		
//		for(ActividadesModel actividad : allActividades) {
//			ActividadesImagenModel actividadImagen; 
//			
//			// OBTENGO LA FECHA ACTUAL
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(actividad.getFecha());
//			int fechaActual = calendar.get(Calendar.YEAR);
//			
//			// OBTENGO EL NOMBRE DE LA FOTO DE UN AÑO
//			File carpeta = new File(this.pathActividades + fechaActual +"/"+ actividad.getId());
//			List<String> archivos = new ArrayList<String>();
//			
//			if(carpeta.listFiles() != null) {
//				for(File archivo : carpeta.listFiles()) {
//					archivos.add(archivo.getName());
//				}
//			}
//			
//			if(fechaActual == 2014) {
//				if(contador == 0) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}else if(fechaActual == 2015) {
//				if(contador == 0) {
//					contador++;
//				}
//				
//				if(contador == 1) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}
//			else if(fechaActual == 2016) {
//				if(contador == 0 || contador == 1) {
//					contador = 2;
//				}
//				
//				if(contador == 2) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}
//			else if(fechaActual == 2017) {
//				if(contador == 0 || contador == 1 || contador == 2) {
//					contador = 3;
//				}
//				
//				if(contador == 3) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}
//			else if(fechaActual == 2018) {
//				if(contador == 0 || contador == 1 || contador == 2 || contador == 3) {
//					contador = 4;
//				}
//				
//				if(contador == 4) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}
//			else if(fechaActual == 2019) {
//				if(contador == 0 || contador == 1 || contador == 2 || contador == 3 || contador == 4) {
//					contador = 5;
//				}
//				
//				if(contador == 5) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}
//			else if(fechaActual == 2020) {
//				if(contador == 0 || contador == 1 || contador == 2 || contador == 3 || contador == 4 || contador == 5) {
//					contador = 6;
//				}
//				
//				if(contador == 6) {
//					String foto = archivos.get(0);
//					actividadPorAnio.add(new ActividadesImagenModel(actividad, foto));
//					contador++;
//				}
//			}	
//		}
//		
//		return actividadPorAnio;
//	}

}
