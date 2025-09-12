package com.example.bankcards.mapper;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardHolder;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class CardMapper {

    @Autowired
    protected EntityManager entityManager;

    @Mapping(target = "cardHolderId", source = "cardHolder.id")
    public abstract CardDTO toDTO(Card entity);

    @Mapping(target = "cardHolder", source = "cardHolderId", qualifiedByName = "mapToReference")
    public abstract Card toEntity(CardDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardHolder", source = "cardHolderId", qualifiedByName = "mapToReference")
    public abstract void updateEntityFromDTO(CardDTO dto, @MappingTarget Card entity);

    @Named("mapToReference")
    protected CardHolder mapToReference(UUID id) {
        return id == null ? null : entityManager.getReference(CardHolder.class, id);
    }
}