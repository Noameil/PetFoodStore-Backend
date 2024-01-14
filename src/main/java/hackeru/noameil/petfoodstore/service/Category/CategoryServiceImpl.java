package hackeru.noameil.petfoodstore.service.Category;

import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderResponseDto;
import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.entity.Order;
import hackeru.noameil.petfoodstore.entity.SubCategory;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.CategoryRepository;
import hackeru.noameil.petfoodstore.repository.ItemRepository;
import hackeru.noameil.petfoodstore.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {

        var entity = modelMapper.map(categoryRequestDto, Category.class);

//        Set<Item> items = new HashSet<>(itemRepository.findAllById(categoryRequestDto.getItems()));
//        Set<SubCategory> subCategories = new HashSet<>(subCategoryRepository.findAllById(categoryRequestDto.getSubCategories()));
//
//        var entity = Category.builder()
//                .categoryName(categoryRequestDto.getCategoryName())
//                .items(items.stream().map(item -> Item.builder()
//                        .itemId(item.getItemId())
//                        .itemName(item.getItemName())
//                        .build())
//                .collect(Collectors.toSet()))
//                .subCategories(subCategories.stream().map(subCategory -> SubCategory.builder()
//                                .subCategoryId(subCategory.getSubCategoryId())
//                                .subCategoryName(subCategory.getSubCategoryName())
//                                .build())
//                        .collect(Collectors.toSet()))
//                .build();

        var saved = categoryRepository.save(entity);

        return modelMapper.map(saved, CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, CategoryResponseDto.class))
                .toList();
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {

        var category = getCategoryEntity(id);
        CategoryResponseDto dto =
                modelMapper.map(category, CategoryResponseDto.class);

        return dto;
    }

    @Override
    public CategoryResponseDto getCategoryByCategoryName(String categoryName) {
        var category = categoryRepository.findByCategoryNameIgnoreCase(categoryName).get();
        CategoryResponseDto dto =
                modelMapper.map(category, CategoryResponseDto.class);

        return dto;
    }

    @Override
    public CategoryResponseDto updateCategoryById(CategoryRequestDto dto, long id) {
        var categoryFromDb = getCategoryEntity(id);

        Set<Item> items = new HashSet<>(itemRepository.findAllById(dto.getItems()));

        categoryFromDb.setCategoryName(dto.getCategoryName());
        categoryFromDb.setItems(items);

        var saved = categoryRepository.save(categoryFromDb);

        return modelMapper.map(saved, CategoryResponseDto.class);
    }

    @Override
    public CategoryDeleteDto deleteCategoryById(Long id) {
        Category category = getCategoryEntity(id);
        category.getItems().forEach(item -> {
            item.getCategories().remove(category);
        });
        itemRepository.saveAll(category.getItems());
        categoryRepository.deleteById(id);
        return modelMapper.map(category, CategoryDeleteDto.class);
    }

    private Category getCategoryEntity(Long id) {
        Category category =
                categoryRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Category", id)
                );
        return category;
    }
}
