import psycopg2


def reset_database(fill_data=True):
    try:
        # Подключение к базе данных
        connection = psycopg2.connect(
            database="SpendSmart",
            user="postgres",
            password="127576",
            host="localhost",
            port="5432"
        )
        cursor = connection.cursor()

        # Сброс и создание таблиц
        cursor.execute("DROP TABLE IF EXISTS db.transactions;")
        cursor.execute("DROP TABLE IF EXISTS db.users;")

        cursor.execute("CREATE SCHEMA IF NOT EXISTS db;")

        cursor.execute("""
            CREATE TABLE IF NOT EXISTS db.users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(80),
                account FLOAT
            );
        """)

        cursor.execute("""
            CREATE TABLE IF NOT EXISTS db.transactions (
                id SERIAL PRIMARY KEY,
                user_id INT REFERENCES db.users(id) ON DELETE CASCADE,
                money_amount FLOAT,
                transaction_type VARCHAR(20) CHECK (transaction_type IN ('deposit', 'withdrawal'))
            );
        """)

        # Заполнение данными, если указано
        if fill_data:
            cursor.execute("""
                INSERT INTO db.users (name, account) VALUES
                ('Alice', 1200.50),
                ('Bob', 950.00),
                ('Charlie', 1500.75),
                ('David', 725.30),
                ('Eva', 1020.10),
                ('Frank', 530.60),
                ('Grace', 1480.00),
                ('Helen', 890.45),
                ('Ian', 670.25),
                ('Jane', 1350.80);
            """)

            cursor.execute("""
                INSERT INTO db.transactions (user_id, money_amount, transaction_type) VALUES
                (1, 200.50, 'deposit'),
                (1, 300.00, 'withdrawal'),
                (3, 500.75, 'deposit'),
                (3, 120.00, 'withdrawal'),
                (5, 700.60, 'deposit'),
                (6, 150.30, 'withdrawal'),
                (7, 450.90, 'deposit'),
                (7, 100.50, 'deposit'),
                (9, 240.40, 'withdrawal'),
                (10, 900.75, 'withdrawal');
            """)

        # Фиксация изменений
        connection.commit()

        print("База данных успешно сброшена и заполнена." if fill_data else "База данных успешно сброшена.")

    except Exception as e:
        print(f"Произошла ошибка: {e}")

    finally:
        # Закрытие курсора и соединения
        if cursor:
            cursor.close()
        if connection:
            connection.close()


if __name__ == "__main__":
    fill_data_choice = input("Хотите заполнить базу данных данными? (1/0): ").strip().lower()
    fill_data = fill_data_choice == '1'
    reset_database(fill_data)
