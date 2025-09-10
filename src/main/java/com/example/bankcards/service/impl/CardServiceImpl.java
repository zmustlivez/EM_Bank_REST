package com.example.bankcards.service.impl;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.mapper.CardMapper;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
    }

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
    public List<CardDTO> readAll(UUID cardholderId) {
        List<Card> cardList = cardRepository.findAllByCardHolderId(cardholderId);
        return cardList.stream().map(cardMapper::toDTO).toList();
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
