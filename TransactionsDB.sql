CREATE DATABASE transactionDB;

CREATE TABLE transactions (
    transaction_external_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_external_id_debit UUID NOT NULL,
    account_external_id_credit UUID NOT NULL,
    transfer_type_id INT NOT NULL,
    value DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL
) PARTITION BY RANGE (created_at);

CREATE TABLE transactions_2024 PARTITION OF transactions
FOR VALUES FROM ('2024-01-01') TO ('2025-01-01');


CREATE TABLE transfer_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

INSERT INTO transfer_types (name) VALUES ('Type1'), ('Type2'), ('Type3');


CREATE INDEX idx_account_external_id_debit ON transactions (account_external_id_debit);
CREATE INDEX idx_account_external_id_credit ON transactions (account_external_id_credit);
CREATE INDEX idx_created_at ON transactions (created_at);

