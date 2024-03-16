package com.arthursouza.swiftflowMVP.exceptions;

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


    
}
