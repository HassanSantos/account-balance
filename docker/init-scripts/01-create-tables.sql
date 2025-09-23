-- Create database extensions if needed
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create accounts table with optimized structure
CREATE TABLE IF NOT EXISTS accounts (
                                        id VARCHAR(36) PRIMARY KEY,
    owner VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('ENABLED', 'DISABLED')),
    balance_amount NUMERIC(15,2) NOT NULL,
    balance_currency VARCHAR(3) NOT NULL,
    created_at_entity TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create transactions table (se necessário para futuras funcionalidades)
CREATE TABLE IF NOT EXISTS transactions (
                                            id VARCHAR(36) PRIMARY KEY,
    account_id VARCHAR(36) NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('CREDIT', 'DEBIT')),
    amount NUMERIC(15,2) NOT NULL,
    currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
    status VARCHAR(20) NOT NULL CHECK (status IN ('APPROVED', 'REJECTED')),
    transaction_timestamp TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
    );

-- Create indexes for performance
CREATE INDEX IF NOT EXISTS idx_accounts_owner ON accounts(owner);
CREATE INDEX IF NOT EXISTS idx_accounts_updated_at ON accounts(updated_at);
CREATE INDEX IF NOT EXISTS idx_accounts_status ON accounts(status);
CREATE INDEX IF NOT EXISTS idx_transactions_account_id ON transactions(account_id);
CREATE INDEX IF NOT EXISTS idx_transactions_timestamp ON transactions(transaction_timestamp);
CREATE INDEX IF NOT EXISTS idx_transactions_type ON transactions(type);

-- Insert sample data for testing
INSERT INTO accounts (id, owner, created_at, status, balance_amount, balance_currency)
VALUES
    ('5b19c8b6-0cc4-4c72-a989-0c2ee15fa975', '315e3cfe-f4af-4cd2-b298-a449e614349a', NOW(), 'ENABLED', 183.12, 'BRL'),
    ('6c29d9c7-1dd5-5d83-baab-1d3ee26gb086', '426f4dgf-g5bg-5de3-c409-b55af72545ab', NOW(), 'ENABLED', 2500.75, 'BRL'),
    ('7d38e0d8-2ee6-6e94-cbbc-2e4ff37hc197', '537g5ehg-h6ch-6ef4-d51a-c66bg83656bc', NOW(), 'DISABLED', 0.00, 'BRL')
    ON CONFLICT (id) DO NOTHING;

-- Create a function to update the updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

-- Create trigger to automatically update updated_at
DROP TRIGGER IF EXISTS update_accounts_updated_at ON accounts;
CREATE TRIGGER update_accounts_updated_at
    BEFORE UPDATE ON accounts
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();