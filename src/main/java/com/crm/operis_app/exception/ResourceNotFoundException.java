package com.crm.operis_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
//public class ResourceNotFoundException extends Exception{
//
//	private static final long serialVersionUID = 1L;
//
//	public ResourceNotFoundException(String message){
//    	super(message);
//    }
//
////	public ResourceNotFoundException() {
////		super();
////	}
////
////	public ResourceNotFoundException(String message) {
////		super(message);
////	}
////
////	public ResourceNotFoundException(String message, Throwable cause) {
////		super(message, cause);
////	}
//
//}
