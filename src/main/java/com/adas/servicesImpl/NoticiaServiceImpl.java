package com.adas.servicesImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adas.DAO.INoticiasDAO;
import com.adas.models.NoticiasModel;
import com.adas.models.UsuariosModel;
import com.adas.services.INoticiaService;

@Service
public class NoticiaServiceImpl implements INoticiaService{

	@Autowired
	private INoticiasDAO noticiasDAO;

	@Override
	public List<NoticiasModel> findAll() {
		return (List<NoticiasModel>) this.noticiasDAO.findAll();
	}

	@Override
	public NoticiasModel findById(Integer id) {
		Optional<NoticiasModel> optional = this.noticiasDAO.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}else return null;
	}

	@Override
	public List<NoticiasModel> findByUsuarioId(Integer id) {
		List<NoticiasModel> noticiasTodas = findAll();
		List<NoticiasModel> noticiasUser = new ArrayList<NoticiasModel>();
		
		for(NoticiasModel noticia : noticiasTodas) {
			if(noticia.getUsuarioId().getId() == id) {
				noticiasUser.add(noticia);
			}
		}
		
		if(noticiasUser.size() > 0) {
			return noticiasUser;
		}else return null;
	}

	@Override
	public NoticiasModel save(NoticiasModel noticia) {
		return this.noticiasDAO.save(noticia);
	}

	@Override
	public void delete(Integer id) {
		this.noticiasDAO.deleteById(id);
	}

	@Override
	public Integer noticiasActuales() {
		Integer contadorNoticias = 0;
		List<NoticiasModel> noticias = findAll();
		
		if(noticias.size() <= 0) {
			contadorNoticias = 0;
		}else {
			contadorNoticias = noticias.get(noticias.size()-1).getId();
		}
		
		return contadorNoticias;
	}
}
