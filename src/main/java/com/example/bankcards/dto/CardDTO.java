package com.example.bankcards.dto;

import com.example.bankcards.enums.CardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDTO {

    private UUID id;

    @NotNull
    @NotBlank(message = "Card number cannot be blank")
    private String number;

    private UUID cardHolderId;

    private String cardHolderName;

    @NotNull
    private YearMonth validThru;

    @NotNull
    private CardStatus status;

    @NotNull
    private BigDecimal balance;
}
