package com.mathias.jabuti.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mathias.jabuti.api.exceptionhandler.ApiError.Field;
import com.mathias.jabuti.domain.exception.EntityNotFoundException;
import com.mathias.jabuti.domain.service.DomainException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomainException(DomainException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError apiError = createProblemBuilder(status, ex.getMessage()).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = createProblemBuilder(status, ex.getMessage()).build();
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatusCode statusCode, WebRequest request) {

        if (body == null) {
            body = ApiError.builder().timestamp(LocalDateTime.now()).message(statusCode.toString())
                    .status(statusCode.value()).build();
        } else if (body instanceof String) {
            body = ApiError.builder().timestamp(LocalDateTime.now()).message((String) body).status(statusCode.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = "Invalid request";
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ApiError.Field> fields = new ArrayList<>();
        errors.forEach(error -> {
            String name = error.getField();
            String errorMessage = error.getDefaultMessage();
            fields.add(Field.builder().name(name).userMessage(errorMessage).build());
        });
        ApiError apiError = createProblemBuilder((HttpStatus) status, message).fields(fields).build();
        return handleExceptionInternal(ex, apiError, headers, status, request);
    }

    private ApiError.ApiErrorBuilder createProblemBuilder(HttpStatus status, String message) {
        return ApiError.builder().message(message).status(status.value());
    }
}