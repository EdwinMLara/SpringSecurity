package com.securyti.demo.controler;

import com.securyti.demo.jwtDemo;
import com.securyti.demo.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldControler{

    @Autowired
    private jwtDemo jwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value="/hello")
    public String paguinaPrinsipal(){
        return "Hello World";
    }

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public String generarToken(@RequestBody Usuario usuario) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getNombre(),usuario.getPassword()));
        }catch(Exception ex){
            throw new Exception("Usuario invalido");
        }
            return null;
    }
}