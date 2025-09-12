--liquibase formatted sql

--changeset mustlive:1
CREATE TABLE IF NOT EXISTS card_holders
(
    id UUID  PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    surname VARCHAR(64) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    created_by VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE,
    updated_by VARCHAR(255),
    updated_at TIMESTAMP WITH TIME ZONE
);
--rollback DROP TABLE card_holders

--changeset mustlive:2
CREATE TABLE IF NOT EXISTS cards
(
    id UUID PRIMARY KEY,
    card_number VARCHAR(16) NOT NULL UNIQUE,
    card_holder_id UUID REFERENCES card_holders (id),
    card_valid_thru DATE NOT NULL,

    card_status VARCHAR(15) DEFAULT 'BLOCKED',
    card_balance NUMERIC(10, 2) NOT NULL,
    created_by VARCHAR(255),
    created_at TIMESTAMP WITH TIME ZONE,
    updated_by VARCHAR(255),
    updated_at TIMESTAMP WITH TIME ZONE
    );
--rollback DROP TABLE cards