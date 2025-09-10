package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.CardHolderDTO;
import com.example.bankcards.service.CardHolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/cardholders")
@AllArgsConstructor
@Tag(name = "CardHolder API", description = "Operations related to cardholder")
public class CardHolderController {

    private final CardHolderService cardHolderService;

    @Operation(summary = "Create a cardholder",
            description = "Return UUID of cardholder if success")
    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CardHolderDTO cardHolderDTO) {
        UUID cardHolderId = cardHolderService.create(cardHolderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardHolderId);
    }

    @Operation(summary = "Read a cardholder by cardholder ID",
            description = "Retrieves a cardholder by its unique identifier (UUID).")
    @GetMapping("/{id}")
    public ResponseEntity<CardHolderDTO> read(@NotNull @PathVariable("id") UUID cardHolderId) {
        CardHolderDTO cardHolderDTO = cardHolderService.read(cardHolderId);
        return ResponseEntity.ok(cardHolderDTO);
    }

    @Operation(summary = "Read all cardholders",
            description = "Retrieves a pager of cardholders")
    @GetMapping("/cardholders")
    public ResponseEntity<Page<CardHolderDTO>> readAllCardHolders(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CardHolderDTO> cardHolderDTO = cardHolderService.readAll(pageable);
        return ResponseEntity.ok(cardHolderDTO);
    }

    @Operation(summary = "Update an existing cardholder",
            description = "Updates an existing cardholder with the provided data and returns the updated cardholder.")
    @PutMapping("/{id}")
    public ResponseEntity<CardHolderDTO> update(@NotNull @PathVariable("id") UUID cardHolderId, @Valid @RequestBody CardHolderDTO cardHolderDTO) {
        CardHolderDTO updateCardHolderDTO = cardHolderService.update(cardHolderId, cardHolderDTO);
        return ResponseEntity.ok(updateCardHolderDTO);
    }

    @Operation(summary = "Delete a cardholder by ID",
            description = "Deletes a cardholder by its unique identifier (UUID) and returns a confirmation message.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@NotNull @PathVariable("id") UUID cardHolderId) {
        cardHolderService.delete(cardHolderId);
        return ResponseEntity.ok("Card " + cardHolderId + " delete successful");
    }
}