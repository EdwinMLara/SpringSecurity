package com.securyti.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.securyti.demo.model.Usuario;
import com.securyti.demo.repositorio.UsuarioRepositorio;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UsuarioRepositorio usuariorepo;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void crearUsuarioText() {
		final Usuario us = new Usuario();
		us.setNombre("EdwinMiguel");
		us.setPassword(encoder.encode("123"));
		Usuario usuarioCreado = usuariorepo.save(us);
		assertTrue(usuarioCreado.getPassword().equalsIgnoreCase(us.getPassword()));
	}

}
