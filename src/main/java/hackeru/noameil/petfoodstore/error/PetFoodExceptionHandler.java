package hackeru.noameil.petfoodstore.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class PetFoodExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e) {

        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problemDetails.setProperty("timestamp", LocalDateTime.now());
        problemDetails.setProperty("resourceName", e.getResourceName());
        problemDetails.setProperty("resourceId", e.getResourceId());

        return problemDetails;
    }

    @ExceptionHandler(PetFoodStoreException.class)
    public ProblemDetail handleBlogException(PetFoodStoreException e) {
        var problemDetail =
                ProblemDetail.
                        forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e) {
        var problemDetail =
                ProblemDetail.
                        forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        //new ProblemDetail Object:
        var problemDetail =
                ProblemDetail.
                        forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");

        //for Each fieldError in the exception:
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            problemDetail.setProperty("Validation Failed for property", fieldError.getField());
            //problemDetail.setProperty("objectName", fieldError.getObjectName());
            problemDetail.setProperty("message", fieldError.getDefaultMessage());
            problemDetail.setProperty("rejectedValue", fieldError.getRejectedValue());
        });

        //add details about the exception:
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return problemDetail;
    }
}
