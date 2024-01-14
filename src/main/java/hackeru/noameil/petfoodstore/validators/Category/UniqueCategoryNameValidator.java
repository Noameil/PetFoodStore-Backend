package hackeru.noameil.petfoodstore.validators.Category;

import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.repository.CategoryRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueCategoryNameValidator implements ConstraintValidator<UniqueCategoryName, String> {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext context) {
        Optional<Category> category = categoryRepository.findByCategoryNameIgnoreCase(categoryName);

        //if category does not exist -> VALID
        return category.isEmpty();
    }
}
