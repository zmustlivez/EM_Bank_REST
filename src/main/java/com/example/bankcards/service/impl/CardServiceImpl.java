package com.example.bankcards.service.impl;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.mapper.CardMapper;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public UUID create(CardDTO cardDTO) {
        cardRepository.existsById(cardDTO.getCardHolderId());//TODO проверить на существование владельца
        Card entity = cardMapper.toEntity(cardDTO);
        entity = cardRepository.save(entity);
        return entity.getId();
    }

    @Override
    public CardDTO read(UUID cardId) {
        Card entity = findById(cardId);
        return cardMapper.toDTO(entity);
    }

    @Override
    public Page<CardDTO> readAll(UUID cardholderId, Pageable pageable) {
        Page<Card> cardList = cardRepository.findAllByCardHolder_Id(cardholderId, pageable);
        return cardList.map(cardMapper::toDTO);
    }

    @Override
    public CardDTO update(UUID cardId, CardDTO cardDTO) {
        Card entity = findById(cardId);
        cardMapper.updateEntityFromDTO(cardDTO, entity);
        return cardMapper.toDTO(cardRepository.save(entity));
    }

    @Override
    public void delete(UUID cardId) {
        cardRepository.deleteById(cardId);
    }

    private Card findById(UUID cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found: " + cardId));
    }
}
