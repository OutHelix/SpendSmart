import psycopg2
from psycopg2 import sql

# Подключение к базе данных PostgreSQL
conn = psycopg2.connect(
    dbname="SpendSmart",
    user="postgres",
    password="127576",
    host="localhost",
    port="5432"
)
conn.autocommit = True
cursor = conn.cursor()

# SQL-команды для создания схемы и таблиц
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

create_transactions_table = """
CREATE TABLE IF NOT EXISTS db.transactions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES db.users(id) ON DELETE CASCADE,
    amount DECIMAL(15, 2) NOT NULL,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    description TEXT
);
"""

# Функция пересоздания таблиц
def recreate_tables():
    cursor.execute(create_schema)
    cursor.execute("DROP TABLE IF EXISTS db.transactions CASCADE;")
    cursor.execute("DROP TABLE IF EXISTS db.users CASCADE;")
    cursor.execute(create_users_table)
    cursor.execute(create_transactions_table)
    print("Таблицы пересозданы.")

# Функция заполнения тестовыми данными
def insert_test_data():
    users_data = [
        ("User1", "Test", "user1", "user1@example.com", "password1"),
        ("User2", "Test", "user2", "user2@example.com", "password2"),
        ("User3", "Test", "user3", "user3@example.com", "password3")
    ]

    # Вставка пользователей
    cursor.executemany(
        "INSERT INTO db.users (first_name, last_name, username, email, password) VALUES (%s, %s, %s, %s, %s);",
        users_data
    )

    print("Тестовые данные добавлены.")

if __name__ == "__main__":
    recreate_tables()

    option = input("Укажите 0 для пустой таблицы или 1 для заполнения тестовыми данными.\n")
    if option == "1":
        insert_test_data()
    elif option != "0":
        print("Неправильный аргумент. Используйте 0 для пустой таблицы или 1 для заполнения тестовыми данными.")

    cursor.close()
    conn.close()
    print("Завершено.")
