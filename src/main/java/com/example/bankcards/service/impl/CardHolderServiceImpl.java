package com.example.bankcards.service.impl;

import com.example.bankcards.dto.CardHolderDTO;
import com.example.bankcards.entity.CardHolder;
import com.example.bankcards.exception.NotFoundException;
import com.example.bankcards.mapper.CardHolderMapper;
import com.example.bankcards.repository.CardHolderRepository;
import com.example.bankcards.service.CardHolderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardHolderServiceImpl implements CardHolderService {

    private final CardHolderRepository cardHolderRepository;
    private final CardHolderMapper cardHolderMapper;

    @Override
    @Transactional
    public UUID create(CardHolderDTO cardHolderDTO) {
        CardHolder entity = cardHolderMapper.toEntity(cardHolderDTO);
        entity = cardHolderRepository.save(entity);
        return entity.getId();
    }

    @Override
    public CardHolderDTO read(UUID cardHolderId) {
        CardHolder entity = findById(cardHolderId);
        return cardHolderMapper.toDTO(entity);
    }

    @Override
    public Page<CardHolderDTO> readAll(Pageable pageable) {
        return cardHolderRepository.findAll(pageable).map(cardHolderMapper::toDTO);
    }

    @Override
    @Transactional
    public CardHolderDTO update(UUID cardHolderId, CardHolderDTO cardHolderDTO) {
        CardHolder entity = findById(cardHolderId);
        cardHolderDTO.setId(entity.getId());
        cardHolderMapper.updateEntityFromDTO(cardHolderDTO, entity);
        return cardHolderMapper.toDTO(cardHolderRepository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID cardHolderId) {//TODO как лучше удалять клиентов и карты?
        cardHolderRepository.deleteById(cardHolderId);
    }

    private CardHolder findById(UUID cardHolderId) {
        return cardHolderRepository.findById(cardHolderId)
                .orElseThrow(() -> new NotFoundException("CardHolder not found: " + cardHolderId));
    }
}
