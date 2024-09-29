CREATE TABLE IF NOT EXISTS db.transactions (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES db.users(id) ON DELETE CASCADE,
    money_amount FLOAT,
    transaction_type VARCHAR(20) CHECK (transaction_type IN ('deposit', 'withdrawal'))
);
