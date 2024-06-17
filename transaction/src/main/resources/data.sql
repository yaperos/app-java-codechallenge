-- Insert Transaction Types
INSERT INTO transaction_types (id, name) VALUES (1, 'Outbound Transfer');
INSERT INTO transaction_types (id, name) VALUES (2, 'Inbound Transfer');
INSERT INTO transaction_types (id, name) VALUES (3, 'Withdrawal');

-- Insert Transaction Statuses
INSERT INTO transaction_statuses (id, name) VALUES (1, 'PENDING');
INSERT INTO transaction_statuses (id, name) VALUES (2, 'APPROVED');
INSERT INTO transaction_statuses (id, name) VALUES (3, 'REJECTED');
