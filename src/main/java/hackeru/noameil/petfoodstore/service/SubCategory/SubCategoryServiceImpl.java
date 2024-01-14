package hackeru.noameil.petfoodstore.service.SubCategory;

import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryResponseDto;
import hackeru.noameil.petfoodstore.entity.Category;
import hackeru.noameil.petfoodstore.entity.Item;
import hackeru.noameil.petfoodstore.entity.SubCategory;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.repository.CategoryRepository;
import hackeru.noameil.petfoodstore.repository.ItemRepository;
import hackeru.noameil.petfoodstore.repository.SubCategoryRepository;
import hackeru.noameil.petfoodstore.service.Category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final ModelMapper modelMapper;
    private final SubCategoryRepository subCategoryRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto) {

//        var entity = modelMapper.map(subCategoryRequestDto, SubCategory.class);

        Set<Item> items = new HashSet<>(itemRepository.findAllById(subCategoryRequestDto.getItems()));
        var category = getCategoryEntity(subCategoryRequestDto.getCategory());

        var entity = SubCategory.builder()
                .subCategoryName(subCategoryRequestDto.getSubCategoryName())
                .category(category)
                .items(items.stream().map(item -> Item.builder()
                                .itemId(item.getItemId())
                                .itemName(item.getItemName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();

        var saved = subCategoryRepository.save(entity);

        return modelMapper.map(saved, SubCategoryResponseDto.class);
    }

    @Override
    public List<SubCategoryResponseDto> getAllSubCategories() {
        return subCategoryRepository
                .findAll()
                .stream()
                .map(sc -> modelMapper.map(sc, SubCategoryResponseDto.class))
                .toList();
    }

    @Override
    public SubCategoryResponseDto getSubCategoryById(Long id) {

        var subCategory = getSubCategoryEntity(id);
        SubCategoryResponseDto dto =
                modelMapper.map(subCategory, SubCategoryResponseDto.class);

        return dto;
    }

    @Override
    public SubCategoryResponseDto updateSubCategoryById(SubCategoryRequestDto dto, long id) {
        var subCategoryFromDb = getSubCategoryEntity(id);

        Set<Item> items = new HashSet<>(itemRepository.findAllById(dto.getItems()));

        subCategoryFromDb.setSubCategoryName(dto.getSubCategoryName());
        subCategoryFromDb.setItems(items);
        subCategoryFromDb.getCategory().setCategoryId(dto.getCategory());

        var saved = subCategoryRepository.save(subCategoryFromDb);

        return modelMapper.map(saved, SubCategoryResponseDto.class);
    }

    @Override
    public SubCategoryDeleteDto deleteSubCategoryById(Long id) {
        SubCategory subCategory = getSubCategoryEntity(id);
        subCategory.getItems().forEach(item -> {
            item.getSubCategories().remove(subCategory);
        });
        itemRepository.saveAll(subCategory.getItems());
        subCategoryRepository.deleteById(id);
        return modelMapper.map(subCategory, SubCategoryDeleteDto.class);
    }

    private SubCategory getSubCategoryEntity(Long id) {
        SubCategory subCategory =
                subCategoryRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("SubCategory", id)
                );
        return subCategory;
    }

    private Category getCategoryEntity(Long id) {
        Category category =
                categoryRepository.findById(id).orElseThrow(
                        () -> new ResourceNotFoundException("Category", id)
                );
        return category;
    }
}
