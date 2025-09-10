package com.example.bankcards.service;

import com.example.bankcards.dto.CardDTO;

import java.util.List;
import java.util.UUID;

public interface CardService {

    UUID create(CardDTO cardDTO);

    CardDTO read(UUID cardId);

    List<CardDTO> readAll(UUID cardholerId);

    CardDTO update(UUID cardId, CardDTO cardDTO);

    void delete(UUID cardId);
}