package com.example.bankcards.repository;

import com.example.bankcards.entity.CardHolder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardHolderRepository extends JpaRepository<CardHolder, UUID> {
}
