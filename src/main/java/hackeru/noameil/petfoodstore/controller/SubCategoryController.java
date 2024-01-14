package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.SubCategory.SubCategoryResponseDto;
import hackeru.noameil.petfoodstore.service.Category.CategoryService;
import hackeru.noameil.petfoodstore.service.SubCategory.SubCategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SubCategoryResponseDto> createSubCategory(
            @RequestBody @Valid SubCategoryRequestDto dto,
            UriComponentsBuilder uriBuilder){

        var saved = subCategoryService.createSubCategory(dto);

        var uri = uriBuilder.path("/api/v1/subcategories/{id}")
                .buildAndExpand(saved.getSubCategoryId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);

    }

    @GetMapping
    public ResponseEntity<List<SubCategoryResponseDto>> getAllSubCategories() {
        return ResponseEntity.ok(subCategoryService.getAllSubCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryResponseDto> getSuCategoryById(@Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryResponseDto> updateSubCategoryById(
            @Valid @NotNull @PathVariable long id,
            @Valid @RequestBody SubCategoryRequestDto dto) {

        return ResponseEntity.ok(subCategoryService.updateSubCategoryById(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubCategoryDeleteDto> deleteSubCategoryId(
            @Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(subCategoryService.deleteSubCategoryById(id));
    }

}
