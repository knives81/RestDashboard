package com.dashboard.restservicedashboard.error;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

  
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e){
    	e.printStackTrace();

    	return e.getMessage();
    }  
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoObjectFoundInDbException.class)
    public String handleException2(Exception e){

    	return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = JSONException.class)
    public String handleException4(Exception e){

    	return e.getMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = BadCredentialsException.class)
    public String xxx(Exception e){

        return e.getMessage();
    }



}
