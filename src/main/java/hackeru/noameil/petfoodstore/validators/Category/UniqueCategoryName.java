package hackeru.noameil.petfoodstore.validators.Category;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //we need to check in runtime
@Constraint(validatedBy = {UniqueCategoryNameValidator.class})
public @interface UniqueCategoryName {
    String message() default "Category already exists";

    //enable validation groups (קבוצות מאפשרות לקבץ ולידטורים יחד)
    Class<?>[] groups() default {};

    //the annotation stores a payload
    //the field value is stored in the payload
    Class<? extends Payload>[] payload() default { };
}
