package com.securyti.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

    @Id
    private int id;
    private String nombre;
    private String password;

    public Usuario(){

    }

    public Usuario(String nombre,String password){
        this.nombre = nombre;
        this.password = password;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getPassword(){
        return this.password;
    }
    
}