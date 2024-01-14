package hackeru.noameil.petfoodstore.controller;

import hackeru.noameil.petfoodstore.dto.Category.CategoryDeleteDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemDeleteDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemRequestDto;
import hackeru.noameil.petfoodstore.dto.Item.ItemResponseDto;
import hackeru.noameil.petfoodstore.error.ResourceNotFoundException;
import hackeru.noameil.petfoodstore.service.Item.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ItemResponseDto> createItem(
            @RequestBody @Valid ItemRequestDto dto,
            UriComponentsBuilder uriBuilder) {

        try {
            var saved = itemService.createItem(dto);
            var uri = uriBuilder.path("/api/v1/items/{id}")
                    .buildAndExpand(saved.getItemId())
                    .toUri();
            return ResponseEntity.created(uri).body(saved);

        } catch (ResourceNotFoundException e){
            ProblemDetail problemDetail = ProblemDetail.forStatus(400);
            problemDetail.setDetail(e.getMessage());
            return ResponseEntity.of(problemDetail).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItemById(
            @Valid @NotNull @PathVariable long id,
            @Valid @RequestBody ItemRequestDto dto) {

        return ResponseEntity.ok(itemService.updateItemById(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDeleteDto> deleteItemById(
            @Valid @NotNull @PathVariable long id) {
        return ResponseEntity.ok(itemService.deleteItemById(id));
    }

}

