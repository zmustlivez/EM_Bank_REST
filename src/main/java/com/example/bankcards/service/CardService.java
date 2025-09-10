package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CardService {

    UUID create(CardDTO cardDTO);

    CardDTO read(UUID cardId);

    Page<CardDTO> readAll(UUID cardholerId, Pageable pageable);

    CardDTO update(UUID cardId, CardDTO cardDTO);

    void delete(UUID cardId);
}