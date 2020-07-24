package com.securyti.demo.controler;

import com.securyti.demo.AuthenticationRequest;
import com.securyti.demo.AuthenticationRespons;
import com.securyti.demo.jwtDemo;
import com.securyti.demo.service.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldControler{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioServicio myuserdetailsServices;

    @Autowired
    private jwtDemo jwtUtilDemo;

    @RequestMapping(value="/hello")
    public String paguinaPrinsipal(){
        return "Hello World";
    }

    @RequestMapping(value="/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generarToken(@RequestBody final AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (final Exception ex) {
            throw new Exception("Usuario invalido");
        }

        final UserDetails userDetails = myuserdetailsServices.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtilDemo.generatedJwt(userDetails);
            
        return ResponseEntity.ok(new AuthenticationRespons(jwt));
    }
}