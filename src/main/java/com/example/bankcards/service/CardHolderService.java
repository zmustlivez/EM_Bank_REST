package com.example.bankcards.service;

import com.example.bankcards.dto.CardHolderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CardHolderService {

    UUID create(CardHolderDTO cardHolderDTO);

    CardHolderDTO read(UUID cardHolderId);

    Page<CardHolderDTO> readAll(Pageable pageable);

    CardHolderDTO update(UUID cardHolderId, CardHolderDTO cardHolderDTO);

    void delete(UUID cardHolderId);

}
