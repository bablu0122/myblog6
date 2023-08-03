package com.blogapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {//status code = 404

    private long id;

    public ResourceNotFoundException(long id){

        super("Resource Not Found For id: "+id);
    }

}
