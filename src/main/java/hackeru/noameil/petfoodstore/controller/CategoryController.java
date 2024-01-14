package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryRequestDto;
import hackeru.noameil.petfoodstore.dto.Category.CategoryResponseDto;
import hackeru.noameil.petfoodstore.service.Category.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryResponseDto> createCategory(
            @RequestBody @Valid CategoryRequestDto dto,
            UriComponentsBuilder uriBuilder){

        var saved = categoryService.createCategory(dto);

        var uri = uriBuilder.path("/api/v1/categories/{id}")
                .buildAndExpand(saved.getCategoryId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);

    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/byName")
    public ResponseEntity<CategoryResponseDto> getCategoryByCategoryName(@Valid @NotNull @PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getCategoryByCategoryName(categoryName));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategoryById(
            @Valid @NotNull @PathVariable long id,
            @Valid @RequestBody CategoryRequestDto dto) {

        return ResponseEntity.ok(categoryService.updateCategoryById(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDeleteDto> deleteCategoryId(
            @Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }

}
