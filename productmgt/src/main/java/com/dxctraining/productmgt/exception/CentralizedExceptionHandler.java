package com.dxctraining.productmgt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

	 @ExceptionHandler(ProductNotFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public String handleProductNotFound(ProductNotFoundException e){
	        System.out.println("inside handleProductNotFound");
	        String msg=e.getMessage();
	        return msg;
	    }

	    @ExceptionHandler(InvalidArgumentException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public String handleInvalidArgument(InvalidArgumentException e){
	        System.out.println("inside handleInvalidArgument");
	        return e.getMessage();
	    }
	   
}
