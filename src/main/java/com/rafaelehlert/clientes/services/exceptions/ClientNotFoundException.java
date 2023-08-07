package com.rafaelehlert.clientes.services.exceptions;

public class ClientNotFoundException extends RuntimeException{
    
    public ClientNotFoundException(String msg){
        super(msg);
    }
}
