package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/cards")
@AllArgsConstructor
@Tag(name = "Cards API", description = "Operations related to cards")
public class CardController {

    private final CardService cardService;


    @Operation(summary = "Create the card",
            description = "Return UUID of card if success")
    @PostMapping
    public ResponseEntity<UUID> create(@Valid @RequestBody CardDTO cardDTO) {
        UUID cardId = cardService.create(cardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardId);
    }

    @Operation(summary = "Read a card by card ID",
            description = "Retrieves a card by its unique identifier (UUID).")
    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> readCard(@NotNull @PathVariable("id") UUID cardId) {
        CardDTO cardDTO = cardService.read(cardId);

        return ResponseEntity.ok(cardDTO);
    }

    @Operation(summary = "Read all cards by cardholder ID",
            description = "Retrieves a list of cards associated with the specified cardholder ID.")
    @GetMapping("/cardholder/{id}")
    public ResponseEntity<List<CardDTO>> readCardsByCardHolderId(@NotNull @PathVariable("id") UUID cardHolderId) {
        List<CardDTO> cardDTO = cardService.readAll(cardHolderId);
        return ResponseEntity.ok(cardDTO);
    }

    @Operation(summary = "Update an existing card",
            description = "Updates an existing card with the provided data and returns the updated card.")
    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> update(@NotNull @PathVariable("id") UUID cardId, @Valid @RequestBody CardDTO cardDTO) {
        CardDTO updateCardDTO = cardService.update(cardId, cardDTO);
        return ResponseEntity.ok(updateCardDTO);
    }

    @Operation(summary = "Delete a card by ID",
            description = "Deletes a card by its unique identifier (UUID) and returns a confirmation message.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@NotNull @PathVariable ("id") UUID cardId) {
        cardService.delete(cardId);
        return ResponseEntity.ok("Card " + cardId + " delete successful");
    }
}
