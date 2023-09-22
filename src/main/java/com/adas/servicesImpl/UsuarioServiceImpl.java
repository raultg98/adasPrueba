package com.adas.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.adas.DAO.IUsuariosDAO;
import com.adas.models.UsuariosModel;
import com.adas.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuariosDAO usuariosDAO;
//	
//	@Autowired
//	private BCryptPasswordEncoder encoder;

	
	@Override
	public List<UsuariosModel> findAll() {
		return (List<UsuariosModel>) this.usuariosDAO.findAll();
	}

	@Override
	public UsuariosModel findById(Integer id) {
		return this.usuariosDAO.findById(id).get();
	}

	@Override
	public void save(UsuariosModel usuario) {
		this.usuariosDAO.save(usuario);
	}
	

	@Override
	public void deleteUsuario(Integer id) {
		this.usuariosDAO.deleteById(id);
	}

	@Override
	public String findPasswordById(Integer id) {
		Optional<UsuariosModel> usuario = this.usuariosDAO.findById(id);
		String contrasenia = usuario.get().getContrasenia();
		
		return contrasenia;
	}
}
