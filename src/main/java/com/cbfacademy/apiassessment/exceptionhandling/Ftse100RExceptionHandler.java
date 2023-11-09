package com.cbfacademy.apiassessment.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Ftse100RExceptionHandler {

@ResponseBody
@ExceptionHandler(CompanyDoesNotExistException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public String handleCompanyDoesNotExistException(CompanyDoesNotExistException e){
    return e.getMessage();
}


@ResponseBody
@ExceptionHandler(CompanyAlreadyExistsException.class)
@ResponseStatus(HttpStatus.FORBIDDEN)
public String handleCompanyAlreadyExistsException(CompanyAlreadyExistsException e){
    return e.getMessage();
}
}



