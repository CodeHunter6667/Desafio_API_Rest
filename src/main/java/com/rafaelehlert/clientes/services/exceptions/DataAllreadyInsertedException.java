package com.rafaelehlert.clientes.services.exceptions;

public class DataAllreadyInsertedException extends RuntimeException{
    
    public DataAllreadyInsertedException(String msg){
        super(msg);
    }
}
