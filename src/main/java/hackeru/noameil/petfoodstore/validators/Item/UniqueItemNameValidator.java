package hackeru.noameil.petfoodstore.validators.Item;

import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.repository.ItemRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UniqueItemNameValidator implements ConstraintValidator<UniqueItemName, String> {

    private final ItemRepository itemRepository;

    @Override
    public boolean isValid(String itemName, ConstraintValidatorContext context) {
        Optional<Item> item = itemRepository.findByItemNameIgnoreCase(itemName);

        //if item does not exist -> VALID
        return item.isEmpty();
    }
}
