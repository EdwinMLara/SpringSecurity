package com.securyti.demo.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldControler{

    @RequestMapping(value="/hello")
    public String paguinaPrinsipal(){
        return "Hello World";
    }
}