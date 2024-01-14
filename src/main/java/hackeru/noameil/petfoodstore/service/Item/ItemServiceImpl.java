package hackeru.noameil.petfoodstore.service.Item;

import hackeru.noameil.petfoodstore.dto.Item.ItemDeleteDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemRequestDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.entity.SubCategory;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.CategoryRepository;
import hackeru.noameil.petfoodstore.repository.ItemRepository;
import hackeru.noameil.petfoodstore.repository.SubCategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;


    @Override
    public ItemResponseDto createItem(ItemRequestDto itemRequestDto) throws ResourceNotFoundException {

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(itemRequestDto.getCategories()));
        Set<SubCategory> subCategories = new HashSet<>(subCategoryRepository.findAllById(itemRequestDto.getSubCategories()));

        if(categories.isEmpty())
            throw new ResourceNotFoundException("category", itemRequestDto.getItemName(), "No such category was found");
        if(subCategories.isEmpty())
            throw new ResourceNotFoundException("subCategory", itemRequestDto.getItemName(), "No such subCategory was found");

        var entity = Item.builder()
                .cost(itemRequestDto.getCost())
                .categories(categories.stream().map(category -> Category.builder()
                                        .categoryId(category.getCategoryId())
                                        .categoryName(category.getCategoryName())
                                        .build())
                                .collect(Collectors.toSet()))
                .subCategories(subCategories.stream().map(subCategory -> SubCategory.builder()
                                .subCategoryId(subCategory.getSubCategoryId())
                                .subCategoryName(subCategory.getSubCategoryName())
                                .build())
                        .collect(Collectors.toSet()))
                .itemName(itemRequestDto.getItemName())
                .imgURL(itemRequestDto.getImgURL())
                .build();

        var saved = itemRepository.save(entity);

        return modelMapper.map(saved, ItemResponseDto.class);
    }

    @Override
    public List<ItemResponseDto> getAllItems() {
        return itemRepository
                .findAll()
                .stream()
                .map(i -> modelMapper.map(i, ItemResponseDto.class))
                .toList();
    }

    @Override
    public ItemResponseDto getItemById(Long id) {
        var item = getItemEntity(id);
        ItemResponseDto dto = modelMapper.map(item, ItemResponseDto.class);

        return dto;
    }

    @Override
    public ItemResponseDto updateItemById(@Valid ItemRequestDto dto, long id) {
        var itemFromDb = getItemEntity(id);

        Set<Category> categories = new HashSet<>(categoryRepository.findAllById(dto.getCategories()));
        Set<SubCategory> subCategories = new HashSet<>(subCategoryRepository.findAllById(dto.getSubCategories()));

        //Update Item Name:
        itemFromDb.setItemName(dto.getItemName());
        itemFromDb.setCost(dto.getCost());
        itemFromDb.setCategories(categories);
        itemFromDb.setSubCategories(subCategories);
        itemFromDb.setImgURL(dto.getImgURL());


        var saved = itemRepository.save(itemFromDb);

        return modelMapper.map(saved, ItemResponseDto.class);
    }

    @Override
    public ItemDeleteDto deleteItemById(Long id) {
        Item item = getItemEntity(id);
        itemRepository.deleteById(id);
        return modelMapper.map(item, ItemDeleteDto.class);
    }

    private Item getItemEntity(Long id) {
        Item item =
                itemRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Item", id)
                );
        return item;
    }
}
