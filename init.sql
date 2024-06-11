CREATE TABLE Transactions (
    transactionExternalId VARCHAR(36) PRIMARY KEY,
    accountExternalIdDebit VARCHAR(255) NOT NULL,
    accountExternalIdCredit VARCHAR(255) NOT NULL,
    transferTypeId INT NOT NULL,
    value NUMERIC NOT NULL,
    transactionType VARCHAR(255) NULL,
    transactionStatus VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL
);
CREATE INDEX idx_transactions_id ON Transactions (transactionExternalId);