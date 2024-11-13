CREATE TABLE IF NOT EXISTS db.users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS db.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(10) CHECK ( type IN ('income', 'expense')) NOT NULL
);

CREATE TABLE IF NOT EXISTS db.accounts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES db.users(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    currency VARCHAR(10) DEFAULT 'RUB'
);

CREATE TABLE IF NOT EXISTS db.transactions (
    id SERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES db.accounts(id) ON DELETE CASCADE,
    category_id INTEGER REFERENCES db.categories(id),
    user_id INTEGER REFERENCES db.users(id) ON DELETE CASCADE,
    amount DECIMAL(15, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);