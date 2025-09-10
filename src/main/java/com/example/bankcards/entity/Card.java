package com.example.bankcards.entity;

import com.example.bankcards.enums.CardStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cards")
public class Card extends BaseEntity{

    @Column(name = "card_number", nullable = false, unique = true)
    @NotBlank(message = "Card number cannot be blank")
    @Size(min = 16, max = 16, message = "Card number must be 16 characters")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_holder_id", nullable = false)
    private CardHolder cardHolder;

    @Transient
    public String getCardHolderName() {
        return cardHolder != null ? cardHolder.getFullName() : null;
    }

    @Column(name = "card_valid_thru", nullable = false)
    private YearMonth validThru;

    @Column(name = "card_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.BLOCKED;

    @Column(name = "card_balance", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Balance cannot be null")
    private BigDecimal balance;

}
