package hackeru.noameil.petfoodstore.error;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ToString
@ResponseStatus(HttpStatus.NOT_FOUND)//
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName; //Post
    private String resourceId; //7
    private String message = "Not Found"; //not found

    //Designated Constructor:
    public ResourceNotFoundException(String resourceName, String resourceId, String message) {
        super("%s with id = %s %s".formatted(resourceName, resourceId, message));
        this.resourceName = resourceName;
        this.resourceId = resourceId;
        this.message = message;
    }

    //Convenience Constructor:
    public ResourceNotFoundException(String resourceName, long resourceId, String message) {
        // use the other constructor
        this(resourceName, String.valueOf(resourceId), message);
    }

    //Convenience Constructor:
    public ResourceNotFoundException(String resourceName, long resourceId) {
        // use the other constructor
        this(resourceName, String.valueOf(resourceId), "Not Found");
    }

    //Convenience Constructor:
    public ResourceNotFoundException(long resourceId) {
        // use the other constructor
        this("Resource", String.valueOf(resourceId), "Not Found");
    }
}
