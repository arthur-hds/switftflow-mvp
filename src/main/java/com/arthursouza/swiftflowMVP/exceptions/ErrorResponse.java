package com.arthursouza.swiftflowMVP.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    
    private final int status;
    private final String message;
    private String TrackTrace;
    private List<ValidationError> errors;

    @Data
    @RequiredArgsConstructor
    private class ValidationError{
        private final String field;
        private final String message;

    }

    public void addValidationError(String field, String message){
        
        if (Objects.nonNull(this.errors)) {
            this.errors = new ArrayList();
        }
        this.errors.add(new ValidationError(field, message));

    }
    
}
