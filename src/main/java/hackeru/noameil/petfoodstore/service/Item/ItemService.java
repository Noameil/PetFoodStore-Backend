package hackeru.noameil.petfoodstore.service.Item;

import hackeru.noameil.petfoodstore.dto.Item.ItemDeleteDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemRequestDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

public interface ItemService {

    ItemResponseDto createItem(ItemRequestDto itemRequestDto) throws ResourceNotFoundException;

    List<ItemResponseDto> getAllItems();

    ItemResponseDto getItemById(Long id);

    ItemResponseDto updateItemById(@Valid ItemRequestDto dto, long id);

    ItemDeleteDto deleteItemById(Long id);

}
