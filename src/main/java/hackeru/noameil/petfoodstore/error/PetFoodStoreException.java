package hackeru.noameil.petfoodstore.error;

public class PetFoodStoreException extends RuntimeException{

    public PetFoodStoreException() {
    }

    public PetFoodStoreException(String message) {
        super(message);
    }

    public PetFoodStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public PetFoodStoreException(Throwable cause) {
        super(cause);

    }

    public PetFoodStoreException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
