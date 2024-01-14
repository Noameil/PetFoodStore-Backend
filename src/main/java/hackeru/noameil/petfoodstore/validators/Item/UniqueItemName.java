package hackeru.noameil.petfoodstore.validators.Item;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //we need to check in runtime
@Constraint(validatedBy = {UniqueItemNameValidator.class})
public @interface UniqueItemName {
    String message() default "Item already exists";

    //enable validation groups (קבוצות מאפשרות לקבץ ולידטורים יחד)
    Class<?>[] groups() default {};

    //the annotation stores a payload
    //the field value is stored in the payload
    Class<? extends Payload>[] payload() default { };
}
