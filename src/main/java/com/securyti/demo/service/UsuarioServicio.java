package com.securyti.demo.service;

import java.util.ArrayList;

import com.securyti.demo.model.Usuario;
import com.securyti.demo.repositorio.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio repo;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Usuario us = repo.findByNombre(username);

        final List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));

        final UserDetails userDetails = (UserDetails) new User(us.getNombre(),us.getPassword(),roles);
        return userDetails;
    }
    
}