package hackeru.noameil.petfoodstore.service.SubCategory;

import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryResponseDto;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponseDto createSubCategory(SubCategoryRequestDto subCategoryRequestDto);

    List<SubCategoryResponseDto> getAllSubCategories();

    SubCategoryResponseDto getSubCategoryById(Long id);

    SubCategoryResponseDto updateSubCategoryById(SubCategoryRequestDto dto, long id);

    SubCategoryDeleteDto deleteSubCategoryById(Long id);

}
