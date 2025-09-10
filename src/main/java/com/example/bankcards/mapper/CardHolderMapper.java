package com.example.bankcards.mapper;

import com.example.bankcards.dto.CardHolderDTO;
import com.example.bankcards.entity.CardHolder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardHolderMapper {

    CardHolderDTO toDTO(CardHolder cardHolder);

    CardHolder toEntity(CardHolderDTO cardHolderDTO);

    void updateEntityFromDTO(CardHolderDTO cardHolderDTO, @MappingTarget CardHolder cardHolder);
}
