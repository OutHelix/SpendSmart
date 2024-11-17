import psycopg2


conn = psycopg2.connect(
    dbname="SpendSmart",
    user="postgres",
    password="127576",
    host="localhost",
    port="5432"
)
conn.autocommit = True
cursor = conn.cursor()

create_schema = "CREATE SCHEMA IF NOT EXISTS db;"

create_users_table = """
CREATE TABLE IF NOT EXISTS db.users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
"""

create_categories_table = """
CREATE TABLE IF NOT EXISTS db.categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(10) CHECK (type IN ('income', 'expense')) NOT NULL
);
"""

create_accounts_table = """
CREATE TABLE IF NOT EXISTS db.accounts (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES db.users(id) ON DELETE CASCADE,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(15, 2) DEFAULT 0.00,
    currency VARCHAR(10) DEFAULT 'RUB'
);
"""

create_transactions_table = """
CREATE TABLE IF NOT EXISTS db.transactions (
    id SERIAL PRIMARY KEY,
    account_id INTEGER REFERENCES db.accounts(id) ON DELETE CASCADE,
    category_id INTEGER REFERENCES db.categories(id),
    user_id INTEGER REFERENCES db.users(id) ON DELETE CASCADE,
    amount DECIMAL(15, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);
"""

def recreate_tables():
    cursor.execute(create_schema)
    cursor.execute("DROP TABLE IF EXISTS db.transactions CASCADE;")
    cursor.execute("DROP TABLE IF EXISTS db.accounts CASCADE;")
    cursor.execute("DROP TABLE IF EXISTS db.categories CASCADE;")
    cursor.execute("DROP TABLE IF EXISTS db.users CASCADE;")
    cursor.execute(create_users_table)
    cursor.execute(create_categories_table)
    cursor.execute(create_accounts_table)
    cursor.execute(create_transactions_table)
    print("Таблицы пересозданы.")

def insert_test_data():
    users_data = [
        ("User1", "Test", "user1", "user1@example.com", "password1"),
        ("User2", "Test", "user2", "user2@example.com", "password2"),
        ("User3", "Test", "user3", "user3@example.com", "password3")
    ]

    categories_data = [
        ("Зарплата", "income"),
        ("Продукты", "expense"),
        ("Коммунальные услуги", "expense")
    ]

    cursor.executemany(
        "INSERT INTO db.users (first_name, last_name, username, email, password) VALUES (%s, %s, %s, %s, %s);",
        users_data
    )

    cursor.executemany(
        "INSERT INTO db.categories (name, type) VALUES (%s, %s);",
        categories_data
    )

    print("Тестовые данные добавлены.")

if __name__ == "__main__":
    recreate_tables()

    option = input("Введите 0 для пустых таблиц или 1 для заполнения тестовыми данными.\n")
    if option == "1":
        insert_test_data()
    elif option != "0":
        print("Неправильный аргумент. Используйте 0 для пустых таблиц или 1 для заполнения тестовыми данными.")

    cursor.close()
    conn.close()
    print("Завершено.")
