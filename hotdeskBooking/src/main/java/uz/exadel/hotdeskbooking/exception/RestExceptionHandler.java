package uz.exadel.hotdeskbooking.exception;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.exadel.hotdeskbooking.dto.ResponseItem;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {RestException.class})
    public HttpEntity<ResponseItem> handleException(RestException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                new ResponseItem(
                        ex.getMessage(),
                        500
                )
        );
    }

    @ExceptionHandler(value = {Exception.class})
    public HttpEntity<ResponseItem> handleException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseItem(
                        "Server error",
                        500
                )
        );
    }


    @ExceptionHandler(OfficeCustomException.class)
    public ResponseEntity<ResponseItem> handleOfficeException(OfficeCustomException exception){
        ResponseItem apiExceptionResponse
                = new ResponseItem(exception.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiExceptionResponse);
    }

    @ExceptionHandler(MapCustomException.class)
    public ResponseEntity<ResponseItem> handleMapException(MapCustomException exception){
        ResponseItem apiExceptionResponse = new ResponseItem(exception.getMessage(), 409);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiExceptionResponse);
    }







}
