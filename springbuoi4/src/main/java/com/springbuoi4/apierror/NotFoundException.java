package com.springbuoi4.apierror;

import org.springframework.util.StringUtils;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class clazz, Long id) {
        super(generateMessage(clazz.getSimpleName(), id));
    }

    private static String generateMessage(String entity , Long id){
        return StringUtils.capitalize(entity) + " was not found for parameters {id=" + id + "}"  ;
    }



}