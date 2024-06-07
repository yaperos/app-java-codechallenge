CREATE TABLE public.transaction (
                             "id" BIGSERIAL PRIMARY KEY,
                             "code" VARCHAR(100) NOT NULL,
                             "account_external_id_debit" VARCHAR(100) NOT NULL,
                             "account_external_id_credit" VARCHAR(100) NOT NULL,
                             "transfer_type" VARCHAR(50) NOT NULL,
                             "value" DECIMAL NOT NULL,
                             "status" VARCHAR(50) NOT NULL,
                             "created_at" TIMESTAMP,
                             "updated_at" TIMESTAMP,
                             CONSTRAINT "uq_code" UNIQUE ("code")
);



CREATE OR REPLACE FUNCTION public.find_all_transactions_sp() RETURNS SETOF transaction AS $$
BEGIN
RETURN QUERY SELECT * FROM public.transaction;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION public.find_transaction_by_code_sp(transaction_code VARCHAR) RETURNS transaction AS $$
DECLARE
tx_result transaction%ROWTYPE;
BEGIN
SELECT * INTO tx_result FROM public.transaction WHERE "code" = transaction_code LIMIT 1;
RETURN tx_result;
END;
$$ LANGUAGE plpgsql;