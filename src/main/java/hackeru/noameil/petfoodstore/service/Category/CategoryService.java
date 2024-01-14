package hackeru.noameil.petfoodstore.service.Category;

import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderRequestDto;
import hackeru.noameil.petfoodstore.dto.Order.OrderResponseDto;

import java.util.List;

public interface CategoryService {

    CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategories();

    CategoryResponseDto getCategoryById(Long id);

    CategoryResponseDto getCategoryByCategoryName(String categoryName);

    CategoryResponseDto updateCategoryById(CategoryRequestDto dto, long id);

    CategoryDeleteDto deleteCategoryById(Long id);

}
