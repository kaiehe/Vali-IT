package ee.bcs.valiit.myExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyErrorHandler {

    @ExceptionHandler(MyApplicationException.class)
    public ResponseEntity<Object> handleSampleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new MyErrorResponse(e.getMessage(), 400), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleError(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new MyErrorResponse("Internal error", 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
