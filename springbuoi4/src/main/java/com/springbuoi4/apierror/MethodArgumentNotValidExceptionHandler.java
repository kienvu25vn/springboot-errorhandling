package com.springbuoi4.apierror;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleInvalidArgument(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage("Validation Error");
        List<ApiValidationError> subError = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String object = ((FieldError) error).getObjectName();
            String fieldName = ((FieldError) error).getField();
            Object rejectValue = ((FieldError) error).getRejectedValue();
            String errorMessage = error.getDefaultMessage();
            subError.add(new ApiValidationError(object,fieldName,rejectValue,errorMessage));
        });
        apiError.setSubErrors(subError);
        return apiError;
}
}
