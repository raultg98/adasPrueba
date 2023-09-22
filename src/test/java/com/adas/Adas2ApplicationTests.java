package com.adas;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.adas.DAO.IUsuariosDAO;
import com.adas.controller.UsuariosController;
import com.adas.models.Cargos;
import com.adas.models.UsuariosModel;
import com.adas.security.Seguridad;
import com.adas.services.IUsuarioService;

@SpringBootTest
class Adas2ApplicationTests {
	
	@InjectMocks
	private UsuariosController usuariosController;
	
	@Mock
	IUsuarioService usuarioService;
	
	@Autowired
	private IUsuariosDAO usuario;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
//	private Seguridad encoder;

	@Test
	void contextLoads() {
		UsuariosModel user = new UsuariosModel();
		
		user.setId(1);
		user.setNombre("Raul");
		user.setCorreo("raul@gmail.com");
//		user.setContrasenia(encoder.encode("1234"));
		user.setContrasenia("1234");
		user.setCargo(Cargos.ADMINISTRADOR);
		user.setAdmin(true);
		
		UsuariosModel retorno = usuario.save(user);
		
		assertTrue(retorno.getContrasenia().equalsIgnoreCase(user.getContrasenia()));
	}

}
