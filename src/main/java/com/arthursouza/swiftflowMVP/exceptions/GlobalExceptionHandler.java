package com.arthursouza.swiftflowMVP.exceptions;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.arthursouza.swiftflowMVP.services.exceptions.AuthorizationException;
import com.arthursouza.swiftflowMVP.services.exceptions.DataBindingViolationException;
import com.arthursouza.swiftflowMVP.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler implements AuthenticationFailureHandler {

    @Value("${server.error.include-exception}")
    private boolean printStrackTrace;

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            String message,
            HttpStatus status,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message);

        if (this.printStrackTrace) {
            errorResponse.setTrackTrace(ExceptionUtils.getStackTrace(exception));
        }

        return ResponseEntity.status(status).body(errorResponse);

    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception exception,
            HttpStatus status,
            WebRequest request) {

        return buildErrorResponse(exception, exception.getMessage(), status, request);

    }

    // ERRORS

    // Not especified internal system error
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleAllUncaughtException(
            Exception exception,
            WebRequest request) {

        final String errorMessage = "Unkown error occurred";

        log.error(errorMessage, exception);

        return buildErrorResponse(
                exception,
                errorMessage,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    // Forget to fields
    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException argumentNotValidException,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error. Check 'errors' field for details");

        for (FieldError fieldError : argumentNotValidException.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());

        }

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    // Repeated value in unique fields
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException integrityViolationException,
            WebRequest request) {

        String errorMessage = integrityViolationException.getMostSpecificCause().getMessage();

        log.error(errorMessage, integrityViolationException);

        return buildErrorResponse(
                integrityViolationException,
                errorMessage,
                HttpStatus.CONFLICT,
                request);

    }

    // Empty fields
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<Object> handleConstraintViolationException(
            ConstraintViolationException violationException,
            WebRequest request) {

        log.error("Failed to validate element", violationException);

        return buildErrorResponse(
                violationException,
                HttpStatus.UNPROCESSABLE_ENTITY,
                request);

    }

    // Non existent data, failed get attempt
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleObjectNotFoundException(
            ObjectNotFoundException objectNotFoundException,
            WebRequest request) {

        log.error("Failed to find the requested element", objectNotFoundException);

        return buildErrorResponse(
                objectNotFoundException,
                HttpStatus.NOT_FOUND,
                request);

    }

    // Delete something linked to another table
    public ResponseEntity<Object> handleDataBindingViolation(
            DataBindingViolationException dataBindingViolationException,
            WebRequest request) {

        log.error("ff", dataBindingViolationException);

        return buildErrorResponse(
                dataBindingViolationException,
                HttpStatus.CONFLICT,
                request);

    }

     @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleAuthenticationException(
        AuthenticationException authenticationException,
        WebRequest request){

        log.error("Failed to authenticate ", authenticationException);

        return buildErrorResponse(
            authenticationException,
            HttpStatus.UNAUTHORIZED,
            request);

        }


    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAuthorizationException(
        AuthorizationException authorizationException,
        WebRequest request){

        log.error("Failed to authorizate ", authorizationException);

        return buildErrorResponse(
            authorizationException,
            HttpStatus.FORBIDDEN,
            request);

        }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDeniedException(
        AccessDeniedException accessDeniedException,
        WebRequest request){

            log.error("Authorization Error", accessDeniedException);

            return buildErrorResponse(
                accessDeniedException,
                HttpStatus.FORBIDDEN, 
                request);

    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        Integer status = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(status);
        response.setContentType("application/json");
        ErrorResponse errorResponse = new ErrorResponse(status, "Email or password is incorrect");
        response.getWriter().append(errorResponse.toJson());

    }

}
