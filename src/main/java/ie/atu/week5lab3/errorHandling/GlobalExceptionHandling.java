package ie.atu.week5lab3.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDetails>> showErrorDetails(MethodArgumentNotValidException mae){


        List<ExceptionDetails> errorList = new ArrayList<>();

        for(FieldError error : mae.getBindingResult().getFieldErrors()){
            ExceptionDetails exceptionDetails = new ExceptionDetails();
            exceptionDetails.setFieldName(error.getField());
            exceptionDetails.setFieldValue(error.getDefaultMessage());

            errorList.add(exceptionDetails);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);

    }





}
