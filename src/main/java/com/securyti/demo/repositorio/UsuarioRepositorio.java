package com.securyti.demo.repositorio;

import com.securyti.demo.model.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer>{
    Usuario findByNombre(String nombre);
    
}