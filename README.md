# Система управления личными финансами

## Описание
Это веб-приложение, которое позволяет пользователям управлять своими личными финансами. Пользователи могут добавлять доходы и расходы, просматривать статистику, создавать бюджеты и устанавливать цели по сбережениям.

## Основные компоненты

### 1. Frontend и Backend на Java (Spring)
- Веб-приложение на Java, с использованием Spring Boot для backend-а и простого фронтенда.
- Создание REST API для взаимодействия между клиентской и серверной частями.
- Интеграция базы данных (PostgreSQL) для хранения информации о транзакциях, доходах, расходах и бюджетах.

### 2. Python для анализа данных и отчетности
- Модуль на Python, который периодически или по запросу анализирует данные пользователя.
- Визуализация данных и создание отчетов с использованием различных python-библиотек.
- Python-скрипты могут быть вызваны через API для выполнения сложных вычислений или анализа данных с возвратом результатов в приложение на Java.

### 3. Функционал приложения
- Регистрация и авторизация пользователей.
- Добавление и удаление транзакций (доходы/расходы).
- Статистика и визуализация данных по месяцам.
- Уведомления о превышении бюджета или достижении целей по сбережениям.
- Импорт/экспорт данных в различных форматах (CSV, JSON).

### 4. Взаимодействие Java и Python
- Java отвечает за основной сервер, обработку запросов и управление данными.
- Python используется для анализа больших данных и визуализации (например, отчеты о расходах).
- Взаимодействие между Java и Python можно организовать через REST API или запуском Python-скриптов на стороне Java-сервера.
